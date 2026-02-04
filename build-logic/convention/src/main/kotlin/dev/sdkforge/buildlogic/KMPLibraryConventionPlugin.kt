package dev.sdkforge.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Gradle convention plugin for Kotlin Multiplatform library projects.
 *
 * This plugin provides standardized configuration for KMP libraries, including:
 * - Automatic application of the Kotlin Multiplatform plugin
 * - Compiler options for expect/actual classes
 * - Android target configuration with JVM 21
 * - iOS framework configuration for all supported architectures
 *
 * ## Features
 *
 * - **Multiplatform Support**: Enables Kotlin Multiplatform compilation
 * - **Expect/Actual Classes**: Configures compiler for multiplatform class declarations
 * - **Android Target**: Sets up Android compilation with JVM 21 target
 * - **iOS Frameworks**: Creates static frameworks for iOS targets
 * - **Architecture Support**: Supports x64, ARM64, and Simulator ARM64
 *
 * ## Usage
 *
 * ```kotlin
 * plugins {
 *     id("dev.sdkforge.buildlogic.library.kmp")
 * }
 * ```
 *
 * ## Targets
 *
 * ### Android
 * - Automatically applies Kotlin Multiplatform plugin
 * - Configures JVM target to Java 21
 * - Integrates with Android build system
 *
 * ### iOS
 * - **iOS x64**: Intel-based iOS devices and simulators
 * - **iOS ARM64**: Apple Silicon iOS devices
 * - **iOS Simulator ARM64**: Apple Silicon iOS simulators
 *
 * ## Compiler Options
 *
 * The plugin automatically configures:
 * - `-Xexpect-actual-classes`: Enables expect/actual class declarations
 * - JVM target: Set to Java 21 for Android compilation
 *
 * ## Example Project Structure
 *
 * ```
 * src/
 * ├── commonMain/          # Common code for all platforms
 * ├── androidMain/         # Android-specific code
 * ├── iosMain/             # iOS-specific code
 * └── commonTest/          # Common tests
 * ```
 *
 * ## Dependencies
 *
 * This plugin requires:
 * - Kotlin Multiplatform plugin
 * - Android Gradle Plugin (for Android target)
 * - Xcode (for iOS compilation)
 *
 * @see [Kotlin Multiplatform Documentation](https://kotlinlang.org/docs/multiplatform.html)
 * @see [Kotlin Multiplatform Gradle Plugin](https://kotlinlang.org/docs/multiplatform-gradle-plugin.html)
 */
class KMPLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
            }

            configure<KotlinMultiplatformExtension> {
                compilerOptions {
                    this.freeCompilerArgs.addAll(
                        "-Xexpect-actual-classes",
                    )
                }

                jvm()

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64(),
                ).forEach {
                    it.binaries.framework {
                        baseName = target.name
                        isStatic = true
                    }
                }
            }
        }
    }
}


