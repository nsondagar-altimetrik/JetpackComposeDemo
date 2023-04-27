plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.apollographql.apollo3") version "3.8.1"
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.nikunjsondagar.graphql_data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kapt {
        correctErrorTypes = true
    }
}

apollo {
    service("service") {
        packageName.set("com.nikunjsondagar.graphql_data")
    }
}

dependencies {
    implementation(libs.apollographql.apollo.runtime)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)
    kaptAndroidTest(libs.hilt.compiler)
}