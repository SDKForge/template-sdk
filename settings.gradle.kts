@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven {
            name = "Maven Central Snapshots"
            setUrl("https://central.sonatype.com/repository/maven-snapshots/")
            content { includeGroupByRegex("^dev\\.sdkforge\\.(.+)\$") }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven {
            name = "Maven Central Snapshots"
            setUrl("https://central.sonatype.com/repository/maven-snapshots/")
            content { includeGroupByRegex("^dev\\.sdkforge\\.(.+)\$") }
        }
        ivy {
            name = "Distributions at https://nodejs.org/dist"
            setUrl("https://nodejs.org/dist")
            patternLayout {
                artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]")
            }
            metadataSources {
                artifact()
            }
            content {
                includeModule("org.nodejs", "node")
            }
        }
        ivy {
            name = "Distributions at https://github.com/yarnpkg/yarn/releases/download"
            setUrl("https://github.com/yarnpkg/yarn/releases/download")
            patternLayout {
                artifact("v[revision]/[artifact](-v[revision]).[ext]")
            }
            metadataSources {
                artifact()
            }
            content {
                includeModule("com.yarnpkg", "yarn")
            }
        }
        ivy {
            name = "Distributions at https://github.com/WebAssembly/binaryen/releases/download"
            setUrl("https://github.com/WebAssembly/binaryen/releases/download")
            patternLayout {
                artifact("version_[revision]/binaryen-version_[revision]-[classifier].[ext]")
            }
            metadataSources {
                artifact()
            }
            content {
                includeModule("com.github.webassembly", "binaryen")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "SDKForgeTemplate"

include(":app-android")
include(":app-desktop")
include(":app-web")
include(":app-shared")
include(":shared")
include(":shared-core")

include(":internal-ktlint")
// uncomment if it's needed for development
// include(":shared-template")
// include(":internal-benchmark")
