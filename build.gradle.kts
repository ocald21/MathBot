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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")

    implementation("com.google.inject:guice:4.1.0")
    implementation("com.google.inject.extensions:guice-multibindings:4.1.0")
    implementation("com.authzee.kotlinguice4:kotlin-guice:1.3.0")
    implementation("com.authzee.kotlinguice4:kotlin-guice-multibindings:1.3.0")

    implementation("com.github.DV8FromTheWorld:JDA:5.0.0-beta.5")
    implementation("net.dv8tion:JDA:5.0.0-beta.5") {
        exclude(module="opus-java")
    }

    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation("org.mariuszgromada.math:MathParser.org-mXparser:5.2.1")

    compileOnly("dev.butter:skspigot:1.0")
}

tasks.shadowJar {
    relocate("org.slf4j", "dev.butter.mathbot.slf4j")
    relocate("com.google.inject", "dev.butter.mathbot.guice")
    relocate("com.authzee.kotlinguice4", "dev.authzee.kotlinguice4")

    archiveFileName.set("MathDiscordBot.jar")
}