// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath(libs.gradlePlugin.android)
        classpath(libs.kotlin)
        classpath(libs.koltin.open)
        classpath(libs.gradlePlugin.safeargs)
        classpath(libs.gradlePlugin.ktlint)
    }
}