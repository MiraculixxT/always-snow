plugins {
    kotlin("jvm") version "2.0.21"
    id("fabric-loom") version "1.8-SNAPSHOT"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0-Beta2"
}

group = "de.miraculixx"
version = "1.0.1"

repositories {
    mavenCentral()
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.3")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.16.9")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.108.0+1.21.3")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.12.3+kotlin.2.0.21")
    modApi("me.shedaniel.cloth:cloth-config-fabric:16.0.141") {
        exclude("net.fabricmc.fabric-api")
    }
    modApi("com.terraformersmc:modmenu:12.0.0-beta.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}