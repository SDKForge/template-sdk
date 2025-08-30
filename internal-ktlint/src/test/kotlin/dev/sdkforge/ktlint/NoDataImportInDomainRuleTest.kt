package dev.sdkforge.ktlint

import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import kotlin.test.Test

class NoDataImportInDomainRuleTest {
    private val wrappingRuleAssertThat = assertThatRule { NoDataImportInDomainRule() }

    @Test
    fun `no 'data' import in 'domain' rule`() {
        val code =
            """
            package dev.sdkforge.shared.domain
            
            import dev.sdkforge.shared.data
            """.trimIndent()
        wrappingRuleAssertThat(code).hasLintViolationWithoutAutoCorrect(
            3,
            1,
            NoDataImportInDomainRule.RULE_VIOLATION_MESSAGE,
        )
    }
}
