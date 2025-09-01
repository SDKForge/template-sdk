package dev.sdkforge.ktlint

import com.pinterest.ktlint.rule.engine.core.api.AutocorrectDecision
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleAutocorrectApproveHandler
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class NoImportAllowedInPackageRule(private val forbiddenPackageImports: Map<String, Set<String>>) :
    Rule(
        ruleId = RuleId("$SDKFORGE_RULE_SET_ID:no-import-allowed-in-package"),
        about = About(
            maintainer = "azazellj",
            repositoryUrl = "https://github.com/SDKForge/template-sdk",
            issueTrackerUrl = "https://github.com/SDKForge/template-sdk/issues",
        ),
    ),
    RuleAutocorrectApproveHandler {
    private lateinit var filePackage: KtPackageDirective
    private val fileImports = mutableSetOf<ASTNode>()

    override fun beforeVisitChildNodes(
        node: ASTNode,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> AutocorrectDecision,
    ) {
        when (node.elementType) {
            KtStubElementTypes.PACKAGE_DIRECTIVE -> filePackage = node.psi as KtPackageDirective
            KtStubElementTypes.IMPORT_DIRECTIVE -> fileImports += node
            else -> Unit
        }
    }

    override fun afterVisitChildNodes(
        node: ASTNode,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> AutocorrectDecision,
    ) {
        if (!::filePackage.isInitialized) return
        if (node.elementType != KtStubElementTypes.IMPORT_LIST) return

        val forbiddenImports = forbiddenPackageImports.entries.find { filePackage.qualifiedName.contains(it.key) }?.value ?: return

        for (fileImport in fileImports) {
            val importName = (fileImport.psi as KtImportDirective).importPath?.pathStr.orEmpty()

            if (forbiddenImports.none { fileImport.text.contains(it) }) continue

            emit(fileImport.startOffset, RULE_VIOLATION_MESSAGE.format(importName, filePackage.qualifiedName), false)
        }
    }

    companion object {
        private const val RULE_VIOLATION_MESSAGE = "Importing '%1s', it is not allowed in '%2s'"
    }
}
