package dev.sdkforge.ktlint

import com.pinterest.ktlint.rule.engine.core.api.AutocorrectDecision
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleAutocorrectApproveHandler
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

// TODO could all 'import in package' rules be made into a single rule with an arguments coming from rule constructor?
class NoDataImportInDomainRule :
    Rule(
        ruleId = RuleId("$SDKFORGE_RULE_SET_ID:no-data-import-in-domain"),
        about = About(
            maintainer = "azazellj",
            repositoryUrl = "https://github.com/SDKForge/template-sdk",
            issueTrackerUrl = "https://github.com/SDKForge/template-sdk/issues",
        ),
    ),
    RuleAutocorrectApproveHandler {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> AutocorrectDecision,
    ) {
        when (node.elementType) {
            KtStubElementTypes.PACKAGE_DIRECTIVE -> {
                val packageDirective = node.psi as KtPackageDirective
                lastPackageName = packageDirective.name
            }

            KtStubElementTypes.IMPORT_DIRECTIVE -> {
                val importDirective = node.psi as KtImportDirective
                currentImportPath = importDirective.importPath?.pathStr.orEmpty()
                val isDataImport = currentImportPath.contains(DATA_MODULE_NAME)
                val isPackageNameValid = lastPackageName.contains(DOMAIN_MODULE_NAME)

                if (isDataImport && isPackageNameValid) {
                    emit(node.startOffset, RULE_VIOLATION_MESSAGE, false)
                }
            }

            else -> {}
        }
    }

    companion object {
        private var lastPackageName = ""
        private var currentImportPath = ""
        private const val DATA_MODULE_NAME = "data"
        private const val DOMAIN_MODULE_NAME = "domain"

        val RULE_VIOLATION_MESSAGE =
            "Importing: $currentImportPath. $DATA_MODULE_NAME module import is not allowed in $DOMAIN_MODULE_NAME module"
    }
}
