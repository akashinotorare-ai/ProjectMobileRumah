plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.hanif_3tia"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.hanif_3tia"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Modul 7 Tahap 4.1: Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Modul 9 Tahap 0.2: DotsIndicator
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    // Modul 9 Tahap 0.2: GridLayout
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    // Modul 9 Tahap 0.2: ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
