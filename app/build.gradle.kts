plugins {
    id("java")
    application
    checkstyle
    jacoco
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.freefair.lombok") version "8.12.1"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

val javalinVersion = "6.4.0"
val slf4jSimpleVersion = "2.0.16"
val jteVersion = "3.1.16"
val junitBomVersion = "5.11.4"
val assertjCoreVersion = "3.27.3"
val h2DatabaseVersion = "2.3.232"
val hikariCPVersion = "6.2.1"
val postgresqlVersion = "42.7.5"
val unirestVersion = "4.4.5"
val mockWebServerVersion = "4.12.0"
val jsoupVersion = "1.18.3"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(platform("org.junit:junit-bom:$junitBomVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.konghq:unirest-java-core:4.4.5")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.4.2")
    implementation("io.javalin:javalin:$javalinVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jSimpleVersion")
    implementation("gg.jte:jte:$jteVersion")
    implementation("io.javalin:javalin-rendering:$javalinVersion")
    implementation("io.javalin:javalin-bundle:$javalinVersion")
    testImplementation("org.assertj:assertj-core:$assertjCoreVersion")
    implementation("com.h2database:h2:$h2DatabaseVersion")
    implementation("com.zaxxer:HikariCP:$hikariCPVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    testImplementation("com.squareup.okhttp3:mockwebserver:$mockWebServerVersion")
    testImplementation("org.apache.httpcomponents.client5:httpclient5:5.4.2")
    implementation("org.jsoup:jsoup:$jsoupVersion")

}

application {
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}