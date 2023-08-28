plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id ("androidx.navigation.safeargs.kotlin")
    id ("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.room_mvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.room_mvvm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    val lifecycle_version = "2.6.1"
    val arch_version = "2.2.0"
    val nav_version = "2.7.0"

    //Dagger

    implementation ("com.google.dagger:hilt-android:2.47")
    implementation ("androidx.security:security-crypto:1.1.0-alpha06")
    //kapt cuối cùng
    kapt ("com.google.dagger:hilt-compiler:2.47")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // Room components
    implementation ("androidx.room:room-runtime:2.5.2")
    kapt ("androidx.room:room-compiler:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")
    androidTestImplementation ("androidx.room:room-testing:2.5.2")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}

hilt {
    enableAggregatingTask = true
}