package dev.sdkforge.ktlint

import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import kotlin.test.Test

class NoUiImportInDataRuleTest {
    private val wrappingRuleAssertThat = assertThatRule { NoUiImportInDataRule() }

    @Test
    fun `no 'ui' import in 'data' rule`() {
        val code =
            """
            package dev.sdkforge.shared.data
            
            import dev.sdkforge.shared.ui
            """.trimIndent()
        wrappingRuleAssertThat(code).hasLintViolationWithoutAutoCorrect(
            3,
            1,
            NoUiImportInDataRule.RULE_VIOLATION_MESSAGE,
        )
    }
}
