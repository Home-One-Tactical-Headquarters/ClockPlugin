plugins {
    alias(libs.plugins.jvm)
    id("java-library")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("org.jetbrains.kotlin.kapt") version "2.1.0"
    id("dk.holonet.plugin") version "0.0.1"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
//    compileOnly(libs.pf4j)
//    kapt(libs.pf4j)

//    compileOnly(libs.holonet.core)
    compileOnly(compose.runtime)
    compileOnly(compose.foundation)
    compileOnly(compose.material)
    compileOnly(compose.ui)
    compileOnly(compose.components.resources)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

holoNetPlugin {
    pluginId.set("clock")
    pluginClass.set("dk.holonet.clock.ClockPlugin")
    pluginProvider.set("Holonet")
    pluginsDir.set(File("${rootProject.projectDir}/lib/build/plugins"))
}