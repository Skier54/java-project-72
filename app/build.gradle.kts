import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("com.github.ben-manes.versions") version "0.52.0"
    id("org.sonarqube") version "6.3.1.5724"
    id("io.freefair.lombok") version "8.14.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
    checkstyle
    application
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

dependencies {
    implementation("com.h2database:h2:2.3.232")
    implementation("com.zaxxer:HikariCP:7.0.2")

    implementation("io.javalin:javalin:6.7.0")
    implementation("io.javalin:javalin-bundle:6.7.0")
    implementation("io.javalin:javalin-rendering:6.7.0")

    implementation("org.slf4j:slf4j-simple:2.0.17")
    //implementation("ch.qos.logback:logback-classic:1.5.18")
    implementation("gg.jte:jte:3.2.1")

    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-launcher")

}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}

tasks.jacocoTestReport { reports { xml.required.set(true) } }

sonar {
    properties {
        property("sonar.projectKey", "Skier54_java-project-72")
        property("sonar.organization", "skier54")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}