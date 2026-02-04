package dev.sdkforge.template.core

/**
 * JVM platform implementation.
 *
 * This actual implementation provides JVM-specific platform information.
 * It uses the JVM system properties to retrieve the current JVM platform name and version.
 *
 * ## Implementation Details
 *
 * - **Name**: Returns operating system name in JVM from `System.getProperty("os.name")`
 * - **Version**: Returns the OS version from `System.getProperty("os.version")`
 *
 * @return A [Platform] instance with JVM-specific information
 */
actual val currentPlatform: Platform = object : Platform {
    /**
     * The JVM platform name.
     *
     * @return Operating system name in JVM
     */
    override val name: String get() = System.getProperty("os.name")

    /**
     * The OS version.
     *
     * @return The OS version as a string (e.g., "26.2" for Mac OS X)
     */
    override val version: String get() = System.getProperty("os.version")
}
