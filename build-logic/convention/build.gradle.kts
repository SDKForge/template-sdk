import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "dev.sdkforge.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("dev.sdkforge.buildlogic.library.android") {
            id = "dev.sdkforge.buildlogic.library.android"
            implementationClass = "dev.sdkforge.buildlogic.AndroidLibraryConventionPlugin"
        }
        register("dev.sdkforge.buildlogic.library.kmp") {
            id = "dev.sdkforge.buildlogic.library.kmp"
            implementationClass = "dev.sdkforge.buildlogic.KMPLibraryConventionPlugin"
        }
        register("dev.sdkforge.buildlogic.library.publishing") {
            id = "dev.sdkforge.buildlogic.library.publishing"
            implementationClass = "dev.sdkforge.buildlogic.PublishingConventionPlugin"
        }
    }
}
