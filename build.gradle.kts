plugins {
    kotlin("jvm") version "2.4.10"
    id("net.fabricmc.fabric-loom") version "1.17.19"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.4.10"
}

group = "de.miraculixx"
version = "1.0.4"

repositories {
    mavenCentral()
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    minecraft("com.mojang:minecraft:26.1.2")
    implementation("net.fabricmc:fabric-loader:0.19.3")
    implementation("net.fabricmc.fabric-api:fabric-api:0.155.2+26.1.2")
    implementation("net.fabricmc:fabric-language-kotlin:1.13.13+kotlin.2.4.10")
    api("me.shedaniel.cloth:cloth-config-fabric:26.1.154") {
        exclude("net.fabricmc.fabric-api")
    }
    api("com.terraformersmc:modmenu:18.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(25)
}
