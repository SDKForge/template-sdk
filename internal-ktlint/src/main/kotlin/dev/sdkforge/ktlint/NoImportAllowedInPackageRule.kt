package dev.sdkforge.ktlint

import com.pinterest.ktlint.rule.engine.core.api.AutocorrectDecision
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleAutocorrectApproveHandler
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import kotlin.text.isNullOrEmpty
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class NoImportAllowedInPackageRule(targetPackages: Pair<String, String>) :
    Rule(
        ruleId = RuleId("$SDKFORGE_RULE_SET_ID:no-import-allowed-in-package"),
        about = About(
            maintainer = "azazellj",
            repositoryUrl = "https://github.com/SDKForge/template-sdk",
            issueTrackerUrl = "https://github.com/SDKForge/template-sdk/issues",
        ),
    ),
    RuleAutocorrectApproveHandler {
    private lateinit var modulePackageName: String
    private val imports = mutableSetOf<KtImportDirective>()
    private val targetImportName = targetPackages.first
    private val targetPackageName = targetPackages.second

    override fun beforeVisitChildNodes(
        node: ASTNode,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> AutocorrectDecision,
    ) {
        when (node.elementType) {
            KtStubElementTypes.PACKAGE_DIRECTIVE -> {
                val packageDirective = node.psi as KtPackageDirective
                modulePackageName = packageDirective.qualifiedName
            }
            KtStubElementTypes.IMPORT_DIRECTIVE -> {
                val importDirective = node.psi as KtImportDirective
                val isNameValid = !importDirective.importPath?.pathStr.isNullOrEmpty()
                if (isNameValid) {
                    imports.add(importDirective)
                }
            }
            else -> {
            }
        }
    }

    override fun afterVisitChildNodes(
        node: ASTNode,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> AutocorrectDecision,
    ) {
        if (node.elementType != KtStubElementTypes.IMPORT_LIST) return
        val isReadyToCompareImports = imports.isNotEmpty() && modulePackageName.contains(targetPackageName)
        if (isReadyToCompareImports) {
            for (import in imports) {
                val importName = import.importPath?.pathStr.orEmpty()
                val isTargetImport = importName.contains(targetImportName)
                if (isTargetImport) {
                    emit(node.startOffset, RULE_VIOLATION_MESSAGE.format(importName, targetImportName, targetPackageName), false)
                    return
                }
            }
        }
    }

    companion object {
        private const val RULE_VIOLATION_MESSAGE =
            "Importing: %1s. %2s module import is not allowed in %3s module"
    }
}
