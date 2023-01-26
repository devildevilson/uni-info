plugins {
  kotlin("multiplatform")
  id("com.android.library")
  //id("org.jetbrains.kotlin.android")
}

kotlin {
  android()

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "common"
    }
  }

  sourceSets {
    val commonMain by getting
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }
    val androidMain by getting
    val androidTest by getting
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
    }
    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }
}

android {
  namespace = "org.uni.info_app"
  compileSdk = 33
  defaultConfig {
    minSdk = 24
    targetSdk = 33
  }
}
dependencies {
  implementation("androidx.core:core-ktx:+")
  implementation("androidx.security:security-crypto-ktx:1.1.0-alpha04")
  //implementation("androidx.core:core-ktx:+")
//  constraints {
//    implementation("org.jetbrains.kotlin:kotlin-sdklib-jdk7:1.7.10")
//    implementation("org.jetbrains.kotlin:kotlin-sdklib-jdk8")
//  }
}
