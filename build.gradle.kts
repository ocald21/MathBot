plugins {
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow").version("7.0.0")
    id("java")
    application
}

group = "dev.butter"
version = "1.0-SNAPSHOT"
project.setProperty("mainClassName", "dev.butter.mathbot.module.Mathbot")

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io/")
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
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")

    compileOnly("com.google.inject:guice:4.1.0")
    compileOnly("com.google.inject.extensions:guice-multibindings:4.1.0")
    compileOnly("com.authzee.kotlinguice4:kotlin-guice:1.3.0")
    compileOnly("com.authzee.kotlinguice4:kotlin-guice-multibindings:1.3.0")

    compileOnly("net.dv8tion:JDA:5.0.0-beta.13") {
        exclude(module="opus-java")
    }

    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation("org.mariuszgromada.math:MathParser.org-mXparser:5.2.1")

    compileOnly(files("C:/Users/omarc/Desktop/wine-test/WineSpigot.jar"))
}

tasks.shadowJar {
    relocate("org.slf4j", "dev.butter.mathbot.slf4j")

    archiveFileName.set("MathDiscordBot.jar")
    destinationDirectory.set(File("C:/Users/omarc/Desktop/wine-test/plugins"))
}