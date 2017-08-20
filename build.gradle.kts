import org.gradle.script.lang.kotlin.extra
import org.gradle.script.lang.kotlin.repositories

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    val kotlinVersion = "1.1.4-2" // won't build with 1.2-M2

    extra["kotlin_version"] = kotlinVersion
    extra["support_version"] = "25.3.1"
    extra["anko_version"] = "0.10.1"

    repositories {
        jcenter()
//        maven { url = uri("http://dl.bintray.com/kotlin/kotlin-eap-1.2") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:2.3.3")
        classpath(kotlin("gradle-plugin", kotlinVersion))

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
//        maven { url = uri("http://dl.bintray.com/kotlin/kotlin-eap-1.2") }
    }
}
