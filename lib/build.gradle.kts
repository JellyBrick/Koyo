plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.5.0"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "com.squareup.okhttp3", name = "okhttp", version = "5.0.0-alpha.11")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-datetime", version = "0.4.0")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version = "1.5.0")
    implementation(group = "com.soywiz.korlibs.krypto", name = "krypto-jvm", version = "2.2.0")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test, test framework
            useKotlinTest("1.8.10")
        }
    }
}
