// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
}

val configFile = files("$rootDir/quality/detekt_config.yml")
val kotlinFiles = "**/*.kt"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"

detekt {
    source.setFrom(projectDir)

    parallel = true

    config.setFrom(configFile)

    buildUponDefaultConfig = true
    ignoreFailures = false

}
