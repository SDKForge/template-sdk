package dev.sdkforge.ktlint

import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import kotlin.test.Test

class NoImportAllowedInPackageRuleTest {

    @Test
    fun `no 'data' import in 'domain' rule`() {
        val assertThatDataInDomainRule = assertThatRule { NoImportAllowedInPackageRule("data" to "domain") }
        val code =
            """
            package dev.sdkforge.shared.domain
            
            import dev.sdkforge.shared.data
            import dev.sdkforge.shared.ui
            import dev.sdkforge.shared.presentation
            """.trimIndent()
        val expectedMessage = "Importing: dev.sdkforge.shared.data. data module import is not allowed in domain module"
        assertThatDataInDomainRule(code).hasLintViolationWithoutAutoCorrect(
            3,
            1,
            expectedMessage,
        )
    }

    @Test
    fun `no 'ui' import in 'domain' rule`() {
        val assertThatUiInDomainRule = assertThatRule { NoImportAllowedInPackageRule("ui" to "domain") }
        val code =
            """
            package dev.sdkforge.shared.domain
            
            import dev.sdkforge.shared.ui
            import dev.sdkforge.shared.data
            import dev.sdkforge.shared.presentation
            """.trimIndent()
        val expectedMessage = "Importing: dev.sdkforge.shared.ui. ui module import is not allowed in domain module"
        assertThatUiInDomainRule(code).hasLintViolationWithoutAutoCorrect(
            3,
            1,
            expectedMessage,
        )
    }

    @Test
    fun `no 'ui' import in 'data' rule`() {
        val assertThatUiDataRule = assertThatRule { NoImportAllowedInPackageRule("ui" to "data") }
        val code =
            """
            package dev.sdkforge.shared.data
            
            import dev.sdkforge.shared.ui
            import dev.sdkforge.shared.presentation
            import dev.sdkforge.shared.domain
            """.trimIndent()
        val expectedMessage = "Importing: dev.sdkforge.shared.ui. ui module import is not allowed in data module"
        assertThatUiDataRule(code).hasLintViolationWithoutAutoCorrect(
            3,
            1,
            expectedMessage,
        )
    }

    @Test
    fun `no 'presentation' import in 'data' rule`() {
        val assertThatPresentationDataRule = assertThatRule { NoImportAllowedInPackageRule("presentation" to "data") }
        val code =
            """
            package dev.sdkforge.shared.data
            
            import dev.sdkforge.shared.presentation
            import dev.sdkforge.shared.ui
            import dev.sdkforge.shared.domain
            """.trimIndent()
        val expectedMessage = "Importing: dev.sdkforge.shared.presentation. presentation module import is not allowed in data module"
        assertThatPresentationDataRule(code).hasLintViolationWithoutAutoCorrect(
            3,
            1,
            expectedMessage,
        )
    }
}
