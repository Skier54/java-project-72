import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("io.freefair.lombok") version "8.13.1"
    id("com.github.ben-manes.versions") version "0.52.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.sonarqube") version "6.3.1.5724"
    id("checkstyle")
    java
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
    implementation("org.postgresql:postgresql:42.7.5")
    implementation("com.zaxxer:HikariCP:6.3.0")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    implementation("org.apache.commons:commons-text:1.13.1")

    implementation("io.javalin:javalin:6.6.0")
    implementation("io.javalin:javalin-bundle:6.6.0")
    implementation("io.javalin:javalin-rendering:6.6.0")

    implementation("gg.jte:jte:3.2.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")

    implementation ("org.jsoup:jsoup:1.19.1")
    implementation ("com.konghq:unirest-java:3.13.0")

    testImplementation("org.assertj:assertj-core:3.27.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.12.0")
    //testImplementation(platform("org.junit:junit-bom:5.11.4"))
    //testImplementation("org.junit.jupiter:junit-jupiter")
    //testImplementation("org.junit.platform:junit-platform-launcher")


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
        //property("sonar.host.url", "https://sonarcloud.io")
    }
}
