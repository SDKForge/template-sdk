import com.dropbox.gradle.plugins.dependencyguard.DependencyGuardPlugin
import com.dropbox.gradle.plugins.dependencyguard.DependencyGuardPluginExtension
import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import kotlinx.kover.gradle.plugin.KoverGradlePlugin
import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    // :app
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    // :shared-*
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.binaryCompatibilityValidator).apply(false)
    alias(libs.plugins.dokka).apply(false)
    alias(libs.plugins.build.logic.library.kmp).apply(false)
    alias(libs.plugins.build.logic.library.android).apply(false)
    alias(libs.plugins.build.logic.library.publishing).apply(false)
    // :app-shared / :shared-ui if present
    alias(libs.plugins.compose.multiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    // applied automatically to all :shared-* modules
    alias(libs.plugins.dependency.guard).apply(false)
    // applied to all modules
    alias(libs.plugins.kover)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.versions)
}

apply(plugin = libs.plugins.build.logic.root.get().pluginId)

allprojects {
    apply<VersionsPlugin>()

    tasks.withType<DependencyUpdatesTask> {
        fun isNonStable(
            version: String,
        ): Boolean {
            val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
            val regex = "^[0-9,.v-]+(-r)?$".toRegex()
            val isStable = stableKeyword || regex.matches(version)
            return isStable.not()
        }
        rejectVersionIf {
            isNonStable(candidate.version)
        }
    }
}

subprojects {
    apply<KtlintPlugin>()

    ktlint {
        reporters {
            reporter(ReporterType.HTML)
        }
    }

    // published code configuration
    if (name.startsWith("shared")) {
        apply<DependencyGuardPlugin>()

        configure<DependencyGuardPluginExtension> {
            configuration("releaseRuntimeClasspath")
        }

        apply<DokkaPlugin>()

        configure<DokkaExtension> {
            // TODO: add shared config
        }
    }

    // apply only to forked projects
    if (rootProject.name != "SDKForgeTemplate") {
        dependencies {
            ktlint(project(":internal-ktlint"))
        }
    }
}

kover {
    reports {
        filters {
            excludes {
                // TODO: add rules
            }
        }
    }
}

dependencies {
    subprojects {
        // published code code coverage
        if (name.startsWith("shared")) {
            apply<KoverGradlePlugin>()
            println("Dynamically used code coverage for ${project.path}")
            kover(this)
        }
    }
}
