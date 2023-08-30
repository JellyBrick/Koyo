plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.9.10"
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "com.squareup.okhttp3", name = "okhttp", version = "5.0.0-alpha.11")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-datetime", version = "0.4.0")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version = "1.5.1")
    implementation(group = "com.soywiz.korlibs.krypto", name = "krypto-jvm", version = "4.0.10")
    implementation(group = "com.benasher44", name = "uuid", version = "0.8.1")
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

val sourcesJar by tasks.registering(Jar::class) {
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        register<MavenPublication>("Koyo") {
            from(components["java"])
            artifact(sourcesJar)
        }
    }
}
