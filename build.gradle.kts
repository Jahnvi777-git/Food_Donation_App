//// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
//    id("com.google.gms.google-services") version "4.4.2" apply false
//}
//// Project-level build.gradle.kts
//
//buildscript {
//    dependencies {
//        classpath("com.android.tools.build:gradle:8.0.0") // Use the appropriate version
//        classpath("com.google.gms:google-services:4.4.2")
//    }
//}
//
//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
//}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0") // Use the appropriate version
        classpath("com.google.gms:google-services:4.4.2")
    }
}

allprojects {
    // This block can be used for other configurations if needed
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}
