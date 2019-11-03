object Modules {
    val app = ":app"
    val remote = ":remote"
    val widget = ":widget"
    val local = ":local"
}

object ApplicationId {
    val id = "developersancho.mvvm"
}

object Versions {
    val minSdk = 21
    val compileSdk = 29
    val targetSdk = 29
    val buildTools = "29.0.2"
    val kotlin = "1.3.50"
    val androidPlugin = "3.5.1"
    val navigation = "2.1.0"
    val appCompat = "1.1.0"
    val coreKtx = "1.1.0"
    val constraintLayout = "1.1.3"
    val material = "1.0.0"
    val supportV4 = "1.0.0"
    val coroutines = "1.3.2"
    val gson = "2.8.6"
    val retrofit = "2.6.2"
    val okhttp = "4.2.2"
    val lifecycle = "2.1.0"
    val koin = "2.0.1"
    val room = "2.2.1"
    val glide = "4.10.0"
    val firebaseCore = "17.2.1"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val kotlinCoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val databinding = "com.android.databinding:compiler:${Versions.androidPlugin}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object BuildPlugins {
    val jitpack = "https://jitpack.io"
    val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val safeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    val androidApplication = "com.android.application"
    val kotlinAndroid = "kotlin-android"
    val kotlinAndroidExtensions = "kotlin-android-extensions"
    val kotlinKapt = "kotlin-kapt"
    val kotlinSafeArgs ="androidx.navigation.safeargs.kotlin"
}