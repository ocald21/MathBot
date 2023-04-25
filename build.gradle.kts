plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow").version("7.0.0")
    id("java")
    application
}

group = "dev.butter"
version = "1.0-SNAPSHOT"
project.setProperty("mainClassName", "dev.butter.mathbot.module.Mathbot")

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

sourceSets {
    main {
        kotlin {
            srcDir("src/main/kotlin")
        }
    }
}

kotlin {
    jvmToolchain(8)
}

dependencies {
    shadow(localGroovy())
    shadow(gradleApi())
    shadow("junit:junit:4.13.2")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.20")

    implementation("com.google.inject:guice:4.1.0")
    implementation("com.google.inject.extensions:guice-multibindings:4.1.0")
    implementation("com.authzee.kotlinguice4:kotlin-guice:1.3.0")
    implementation("com.authzee.kotlinguice4:kotlin-guice-multibindings:1.3.0")

    implementation("com.github.DV8FromTheWorld:JDA:5.0.0-beta.5")
    implementation("net.dv8tion:JDA:5.0.0-beta.5") {
        exclude(module="opus-java")
    }
    implementation("ch.qos.logback:logback-classic:1.2.8")
    implementation("org.mariuszgromada.math:MathParser.org-mXparser:5.2.1")

    compileOnly("org.bukkit:bukkit:1.8.8-R0.1-SNAPSHOT")
}

tasks.shadowJar {
    minimize()
    archiveFileName.set("MathDiscordBot.jar")
}

tasks.test {
    useJUnitPlatform()
}

