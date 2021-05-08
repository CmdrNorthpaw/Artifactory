plugins {
    id("fabric-loom") version "0.7-SNAPSHOT"
    id ("maven-publish")
    kotlin("jvm") version "1.5.0"
}

val minecraftVersion: String by project
val yarnMappings: String by project
val loaderVersion: String by project
val modVersion: String by project
val archivesBaseName: String by project
val mavenGroup: String by project
val fabricVersion: String by project

version = modVersion
group = mavenGroup

dependencies {
    //to change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    modImplementation("net.fabricmc", "fabric-language-kotlin", "1.6.0+kotlin.1.5.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.2.0")

    // PSA: Some older mods, compiled on Loom 0.2.1, might have outdated Maven POMs.
    // You may need to force-disable transitiveness on them.
}

tasks.getByName<ProcessResources>("processResources") {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand(
            mutableMapOf(
                "version" to version
            )
        )
    }
}

// ensure that the encoding is set to UTF-8, no matter what the system default is
// this fixes some edge cases with special characters not displaying correctly
// see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
// if it is present.
// If you remove this task, sources will not be generated


repositories {
    mavenCentral()
    maven { url = uri("https://maven.fabricmc.net") }
    maven { url = uri("https://libraries.minecraft.net") }
}
