plugins {
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
    application
}

group = "dev.butter"
version = "1.0"
project.setProperty("mainClassName", "dev.butter.mathbot.module.Mathbot")

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

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io/")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")

    implementation("com.google.inject:guice:4.1.0")
    implementation("com.google.inject.extensions:guice-multibindings:4.1.0")
    implementation("com.authzee.kotlinguice4:kotlin-guice:1.3.0")
    implementation("com.authzee.kotlinguice4:kotlin-guice-multibindings:1.3.0")

    implementation("ch.qos.logback:logback-classic:1.4.12")
    implementation("org.mariuszgromada.math:MathParser.org-mXparser:5.2.1")

    implementation("net.dv8tion:JDA:5.0.0-beta.13") {
        exclude(module="opus-java")
    }

    compileOnly("com.destroystokyo.paper:paper-api:1.9.4-R0.1-SNAPSHOT")
}

tasks.shadowJar {
    relocate("org.slf4j", "dev.butter.mathbot.slf4j")
    relocate("org.mariuszgromada.math", "dev.butter.mathbot.mathparser")
    relocate("com.authzee.kotlinguice4", "dev.butter.mathbot.kotlinguice4")
    relocate("com.google.inject", "dev.butter.mathbot.inject")
    relocate("com.google.inject.extensions", "dev.butter.mathbot.inject.extensions")

    archiveFileName.set("MathDiscordBot.jar")
}