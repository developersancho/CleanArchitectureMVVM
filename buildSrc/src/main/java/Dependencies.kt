object Modules {
    const val app = ":app"
    const val remote = ":core:remote"
    const val local = ":core:local"
    const val manager = ":core:manager"
    const val util = ":common:util"
    const val widget = ":common:widget"
}

object ApplicationId {
    val id = "developersancho.mvvm"
}

object Versions {
    const val minSdk = 19
    const val compileSdk = 29
    const val targetSdk = 29
    const val buildTools = "29.0.2"
    const val kotlinPlugin = "1.3.61"
    const val androidPlugin = "3.6.0-rc01"

    const val navigation = "2.1.0"
    const val appCompat = "1.1.0"
    const val coreKtx = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val material = "1.1.0-beta02"
    const val preference = "1.1.0"

    const val legacy = "1.0.0"
    const val coroutines = "1.3.3"
    const val gson = "2.8.6"
    /* 21+
     const val retrofit = "2.7.0"
     const val okhttp = "4.3.0"
     const val mockWebServer = "4.3.0"
     */
    // 19+
    // important coroutines with retrofit only get directly response min 2.6.0
    const val retrofit = "2.6.0"
    const val okhttp = "3.12.6"
    const val mockWebServer = "3.12.6"

    const val multidex = "2.0.1"

    const val lifecycle = "2.1.0"
    const val lifeCycleKtx = "2.2.0-rc03"
    const val firebaseMessage = "20.1.0"
    const val firebaseCore = "17.2.1"

    const val koin = "2.0.1"
    const val room = "2.2.1"
    const val glide = "4.10.0"

    const val junit = "4.13"
    const val junitExt = "1.1.1"
    const val espresso = "3.2.0"
    const val retrofitCoroutines = "0.9.2"
    const val robolectric = "4.3.1"
    const val timber = "4.7.1"
}

object Libraries {
    // kotlin - coroutine
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinPlugin}"
    val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val kotlinCoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val kotlinReflection = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinPlugin}"

    // android
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val material = "com.google.android.material:material:${Versions.material}"
    val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    val dynamicAnimation = "androidx.dynamicanimation:dynamicanimation:${Versions.legacy}"
    val preference = "androidx.preference:preference:${Versions.preference}"

    // multidex
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    // databinding
    val databinding = "com.android.databinding:compiler:${Versions.androidPlugin}"

    // firebase
    val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    val firebaseMessage = "com.google.firebase:firebase-messaging:${Versions.firebaseMessage}"

    // glide for image
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // room database for local
    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // koin for di
    val koinCore = "org.koin:koin-core:${Versions.koin}"
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    // lifecycle
    val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    val lifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    val lifeCycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleKtx}"
    val lifeCycleRunTimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleKtx}"
    val lifeCycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"

    // retrofit
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitConvertorGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofitCoroutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"

    // okhttp
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // navigation
    val navFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    val navUi = "androidx.navigation:navigation-ui:${Versions.navigation}"

    val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"


    // test
    val koinTest = "org.koin:koin-test:${Versions.koin}"
    val lifeCycleTest = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    val junit = "junit:junit:${Versions.junit}"
    val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    val roboelectric = "org.robolectric:robolectric:${Versions.robolectric}"
}

object BuildPlugins {
    val jitpack = "https://jitpack.io"

    val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinPlugin}"
    val safeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    val supportLibraryVectorDrawables = true
    val multidexEnable = true
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}