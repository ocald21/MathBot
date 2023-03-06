import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow").version("7.0.0")
    id("java")
    application
}

group = "dev.butter"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.inject:guice:5.1.0")
    implementation("com.google.inject.extensions:guice-multibindings:4.2.3")
    implementation("com.github.DV8FromTheWorld:JDA:5.0.0-beta.5")
    implementation("net.dv8tion:JDA:5.0.0-beta.5") {
        exclude(module="opus-java")
    }
    implementation("ch.qos.logback:logback-classic:1.2.8")
    implementation("org.mariuszgromada.math:MathParser.org-mXparser:5.2.1")
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "dev.butter.mathbot.MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.named("shadowJar", ShadowJar::class.java) {
    dependsOn("jar")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "dev.butter.mathbot.MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("dev.butter.mathbot.MainKt")
}