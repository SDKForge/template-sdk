package dev.sdkforge.ktlint

import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import com.pinterest.ktlint.test.LintViolation
import kotlin.test.Test

class NoImportAllowedInPackageRuleTest {

    @Test
    fun `no 'data', 'ui' import in 'domain' rule`() {
        val assertThatDataInDomainRule = assertThatRule { NoImportAllowedInPackageRule(mapOf("domain" to setOf("data", "ui"))) }
        val code =
            """
            package dev.sdkforge.shared.domain
            
            import dev.sdkforge.shared.data
            import dev.sdkforge.shared.ui
            import dev.sdkforge.shared.presentation
            """.trimIndent()
        assertThatDataInDomainRule(code).hasLintViolationsWithoutAutoCorrect(
            LintViolation(3, 1, "Importing 'dev.sdkforge.shared.data', it is not allowed in 'dev.sdkforge.shared.domain'", false),
            LintViolation(4, 1, "Importing 'dev.sdkforge.shared.ui', it is not allowed in 'dev.sdkforge.shared.domain'", false),
        )
    }

    @Test
    fun `no 'ui', 'presentation' import in 'data' rule`() {
        val assertThatUiDataRule = assertThatRule { NoImportAllowedInPackageRule(mapOf("data" to setOf("ui", "presentation"))) }
        val code =
            """
            package dev.sdkforge.shared.data
            
            import dev.sdkforge.shared.ui
            import dev.sdkforge.shared.presentation
            import dev.sdkforge.shared.domain
            """.trimIndent()
        assertThatUiDataRule(code).hasLintViolationsWithoutAutoCorrect(
            LintViolation(3, 1, "Importing 'dev.sdkforge.shared.ui', it is not allowed in 'dev.sdkforge.shared.data'", false),
            LintViolation(4, 1, "Importing 'dev.sdkforge.shared.presentation', it is not allowed in 'dev.sdkforge.shared.data'", false),
        )
    }
}
