// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        maven("https://jitpack.io")
    }
    dependencies {
        val nav_version = "2.7.4"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
allprojects {
    repositories {
        //maven ("https://jitpack.io" )
    }
}
plugins {
    id("com.android.application") version "8.1.2" apply false
}