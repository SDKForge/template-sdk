package dev.sdkforge.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.abi.AbiValidationMultiplatformExtension

class BinaryValidationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configure<KotlinMultiplatformExtension> {
                @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
                extensions.configure<AbiValidationMultiplatformExtension> {
                    enabled.set(true)
                }
            }
        }
    }
}
