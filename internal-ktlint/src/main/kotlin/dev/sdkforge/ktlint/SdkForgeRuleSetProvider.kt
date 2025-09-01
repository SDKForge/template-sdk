package dev.sdkforge.ktlint

import com.pinterest.ktlint.cli.ruleset.core.api.RuleSetProviderV3
import com.pinterest.ktlint.rule.engine.core.api.RuleProvider
import com.pinterest.ktlint.rule.engine.core.api.RuleSetId

internal const val SDKFORGE_RULE_SET_ID = "sdkforge-ktlint-rules"

class SdkForgeRuleSetProvider : RuleSetProviderV3(RuleSetId(SDKFORGE_RULE_SET_ID)) {
    private val forbiddenPackagesCombinations = mapOf(
        "domain" to listOf("data", "ui"),
        "data" to listOf("ui", "presentation"),
    )
    override fun getRuleProviders(): Set<RuleProvider> = setOf(
        RuleProvider { NoTemplateImportRule() },
        RuleProvider { NoTemplatePackageRule() },
        *getForbiddenPackagesCombinationsRules().toTypedArray(),
    )

    private fun getForbiddenPackagesCombinationsRules(): MutableSet<RuleProvider> {
        val combinationSet = mutableSetOf<RuleProvider>()
        for (packageName in forbiddenPackagesCombinations) {
            for (importName in packageName.value) {
                combinationSet.add(RuleProvider { NoImportAllowedInPackageRule(importName to packageName.key) })
            }
        }
        return combinationSet
    }
}
