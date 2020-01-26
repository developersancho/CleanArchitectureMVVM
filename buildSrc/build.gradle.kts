import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

// Required since Gradle 4.10+.
repositories {
    jcenter()
    gradlePluginPortal()
    mavenCentral()
}