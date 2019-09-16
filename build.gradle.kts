buildscript {
    repositories {
        google()
        jcenter()
    }

    val android_gradle_plugin_version = "3.5.0"
    val kotlin_version = "1.3.50"
    val navigation_version = "2.1.0"

    dependencies {
        classpath("com.android.tools.build:gradle:$android_gradle_plugin_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigation_version")
    }
}

group = "br.com.devsrsouza.mcheads"
version = "0.0.1"

allprojects {
    repositories {
        google()
        jcenter()
    }
}

