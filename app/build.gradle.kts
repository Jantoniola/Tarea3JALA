plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "dam.pmdm.tarea3jala"
    compileSdk = 34

    defaultConfig {
        applicationId = "dam.pmdm.tarea3jala"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    //Dependencias para el RecyclerView y la CardView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    //Dependencias para el controlador de navegación
    implementation("androidx.navigation:navigation-ui:2.8.4")
    implementation("androidx.navigation:navigation-fragment:2.8.3")

    //Picasso
    implementation ("com.squareup.picasso:picasso:2.8")

    // ViewModel

    implementation ("androidx.recyclerview:recyclerview:1.2.1")

    // FireBase

    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation ("com.firebaseui:firebase-ui-auth:7.2.0")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.recyclerview:recyclerview:1.1.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.preference)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}