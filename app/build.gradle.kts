import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

val graphQlProperties = Properties()
graphQlProperties.load(FileInputStream(rootProject.file("local.properties")))

android {
    namespace = "com.nikunjsondagar.composedemo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.nikunjsondagar.composedemo"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"


        buildConfigField("String","API_TOKEN",graphQlProperties["API_TOKEN"].toString())
        buildConfigField("String","SERVER_URL",graphQlProperties["SERVER_URL"].toString())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":feature-users"))
    implementation(project(":feature-repos"))
    implementation(project(":feature-settings"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}