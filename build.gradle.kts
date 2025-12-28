plugins {
    kotlin("jvm") version "2.2.21"
    id("fabric-loom") version "1.14-SNAPSHOT"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0-Beta2"
}

group = "de.miraculixx"
version = "1.0.4"

repositories {
    mavenCentral()
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.11")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.18.1")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.140.2+1.21.11")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.8+kotlin.2.3.0")
    modApi("me.shedaniel.cloth:cloth-config-fabric:21.11.153") {
        exclude("net.fabricmc.fabric-api")
    }
    modApi("com.terraformersmc:modmenu:16.0.0-rc.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}