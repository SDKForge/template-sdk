package dev.sdkforge.ktlint

import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import kotlin.test.Test

class NoTemplatePackageRuleTest {
    private val wrappingRuleAssertThat = assertThatRule { NoTemplatePackageRule() }

    private val code =
        """
        class Config(
            var namespace: String,
            var compileSdk: Int,
        )
        
        fun android(action: Config.() -> Unit) = Unit
        
        fun main() {
            androidLibrary {
                namespace = "dev.sdkforge.template.android"
                compileSdk = 37
            }
        } 
        """.trimIndent()

    @Test
    fun `no 'template' package rule`() {
        wrappingRuleAssertThat(code).hasLintViolationWithoutAutoCorrect(
            line = 10,
            col = 9,
            detail = "Using 'dev.sdkforge.template.android' which is a template package.",
        )
    }
}
