import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

repositories {
    jcenter()
}

dependencies {
    implementation(exposed("core"))
    implementation(exposed("dao"))
    implementation(exposed("jdbc"))
    implementation(exposed("java-time"))
    runtimeOnly(h2())

    testImplementation(kotlin("test-junit"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

/**
 * [Exposed](https://github.com/JetBrains/Exposed/releases/latest)
 */
fun exposed(module: String) = "org.jetbrains.exposed:exposed-$module:0.28.1"


/**
 * [H2](https://github.com/h2database/h2database/releases/latest)
 */
fun h2() = "com.h2database:h2:1.4.200"
