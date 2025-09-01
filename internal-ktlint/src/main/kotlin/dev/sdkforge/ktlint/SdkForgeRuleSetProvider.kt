package dev.sdkforge.ktlint

import com.pinterest.ktlint.cli.ruleset.core.api.RuleSetProviderV3
import com.pinterest.ktlint.rule.engine.core.api.RuleProvider
import com.pinterest.ktlint.rule.engine.core.api.RuleSetId

internal const val SDKFORGE_RULE_SET_ID = "sdkforge-ktlint-rules"

class SdkForgeRuleSetProvider : RuleSetProviderV3(RuleSetId(SDKFORGE_RULE_SET_ID)) {

    override fun getRuleProviders(): Set<RuleProvider> = setOf(
        RuleProvider { NoTemplateImportRule() },
        RuleProvider { NoTemplatePackageRule() },
        RuleProvider { NoImportAllowedInPackageRule(FORBIDDEN_PACKAGE_IMPORTS) },
    )

    companion object {
        private val FORBIDDEN_PACKAGE_IMPORTS = mapOf(
            "domain" to setOf("data", "ui"),
            "data" to setOf("ui", "presentation"),
        )
    }
}
