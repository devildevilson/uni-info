plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  namespace = "org.uni.info_app.android"
  compileSdk = 33
  defaultConfig {
    applicationId = "org.uni.info_app.android"
    minSdk = 24
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.0"
  }
  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
}

dependencies {
  implementation(project(":common"))
  implementation("androidx.compose.ui:ui:1.3.3")
  implementation("androidx.compose.ui:ui-tooling:1.3.3")
  implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
  implementation("androidx.compose.runtime:runtime-livedata:1.3.3")
  implementation("androidx.compose.foundation:foundation:1.3.1")
  implementation("androidx.compose.material:material:1.3.1")
  implementation("androidx.activity:activity-compose:1.6.1")
  implementation("androidx.appcompat:appcompat:1.6.0")
  implementation("androidx.core:core-ktx:+")
}