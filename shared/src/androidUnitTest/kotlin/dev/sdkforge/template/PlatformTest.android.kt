@file:Suppress("ktlint:standard:filename")

package dev.sdkforge.template

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidPlatformTest {

    @Test
    fun testPlatformDefinition() {
        assertTrue("Check Android is mentioned", currentPlatform.name.contains("Android", ignoreCase = true))
    }
}
