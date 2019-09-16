plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(28)
}

val kotlin_coroutines_version = "1.3.1"

val core_version = "1.1.0" // 1.2.0-alpha04
val appcompat_version = "1.1.0"
val recyclerview_version = "1.0.0" // 1.1.0-beta04
val lifecycle_version = "2.1.0" // 2.2.0-alpha04
val navigation_version = "2.1.0" // 2.2.0-alpha02
val constraint_layout_version = "1.1.3" // 2.0.0-beta02
val legacy_support_v4_version = "1.0.0"
val room_version = "2.1.0" // 2.2.0-rc01 (para salvar localmente as heads)

val coil_version = "0.7.0"
val retrofit_version = "2.6.0"

dependencies {
    implementation(kotlin("stdlib"))

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_coroutines_version")

    implementation("androidx.appcompat:appcompat:$appcompat_version")
    implementation("androidx.core:core-ktx:$core_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraint_layout_version")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation_version")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation_version")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:$recyclerview_version")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycle_version")

    // room
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    implementation("androidx.legacy:legacy-support-v4:$legacy_support_v4_version")



    // coil
    implementation("io.coil-kt:coil:0.7.0")
}
