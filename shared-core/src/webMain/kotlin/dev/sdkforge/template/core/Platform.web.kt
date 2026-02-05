package dev.sdkforge.template.core

/**
 * Web platform implementation.
 *
 * This actual implementation provides WEB-specific platform information.
 * It uses the WEB navigator properties to retrieve the current WEB navigator name and version.
 *
 * ## Implementation Details
 *
 * - **Name**: Returns navigator name in WEB from `window.navigator.platform`
 * - **Version**: Returns the navigator version from `window.navigator.appVersion`
 *
 * @return A [Platform] instance with WEB-specific information
 */
actual val currentPlatform: Platform = object : Platform {
    /**
     * The navigator platform name.
     *
     * @return navigator name
     */
    override val name: String get() = kotlinx.browser.window.navigator.platform

    /**
     * The navigator version.
     *
     * @return The navigator version as a string (e.g., "5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
     */
    override val version: String get() = kotlinx.browser.window.navigator.appVersion
}
