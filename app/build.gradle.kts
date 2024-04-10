plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.safe.args)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.hk210.newsreader"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hk210.newsreader"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"fb559642d8d64dbab8006cbfa46cc68e\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
        viewBinding {
            enable = true
        }
    }
}

dependencies {

    // Default
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)

    // Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.converter.gson)

    // Okhttp and interceptor
    implementation(libs.okhttp)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.stetho.okhttp3)

    // Navigation
    implementation(libs.navigation.ktx)
    implementation(libs.navigation.ui.ktx)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.kapt)

    implementation(libs.github.glide)

    // Splash Screen
    implementation(libs.androidx.core.splashscreen)

    // Room
    kapt(libs.androidx.room.runtime)
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.ktx)

    implementation(libs.androidx.paging.runtime.ktx)
    implementation (libs.androidx.paging.rxjava3)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}