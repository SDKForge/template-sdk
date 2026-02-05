plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    wasmJs {
        outputModuleName = "app-web"
        browser {
            commonWebpackConfig {
                outputFileName = "app-web.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        webMain {
            dependencies {
                api(projects.appShared)
            }
        }
    }
}
