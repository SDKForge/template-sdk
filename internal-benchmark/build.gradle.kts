import com.android.build.api.dsl.LibraryExtension

plugins {
    // TODO: benchmark gradle plugin is not AGP 9.0 compatible; remove & uncomment on the update
    id("com.android.library")
    // alias(libs.plugins.build.logic.library.android)
    alias(libs.plugins.benchmark)
}

configure<LibraryExtension> {
    // TODO: benchmark gradle plugin is not AGP 9.0 compatible; remove on the update
    compileSdk = 36 // Android 16 (API level 36)

    namespace = "dev.sdkforge.benchmark"

    defaultConfig {
        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
        testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] = "EMULATOR,LOW-BATTERY"
    }

    testBuildType = "release"

    buildTypes {
        debug {
            // Since isDebuggable can"t be modified by gradle for library modules,
            // it must be done in a manifest - see src/androidTest/AndroidManifest.xml
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "benchmark-proguard-rules.pro",
            )
        }
        release {
            isDefault = true
        }
    }
}

dependencies {
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.benchmark.junit4)
}
