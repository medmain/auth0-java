plugins {
    java
    jacoco
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.compileJava {
    options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:unchecked", "-Werror"))
}

tasks.compileTestJava {
    options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Werror"))
}

tasks.test {
    testLogging {
        events("skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.SHORT
    }
}

val okhttpVersion = "4.12.0"
val hamcrestVersion = "3.0"

dependencies {
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.1")
    implementation("com.auth0:java-jwt:4.5.0")
    implementation("net.jodah:failsafe:2.4.4")

    testImplementation("org.bouncycastle:bcprov-jdk18on:1.81")
    testImplementation("org.mockito:mockito-core:5.18.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:$okhttpVersion")
    testImplementation("org.hamcrest:hamcrest-core:$hamcrestVersion")
    testImplementation("org.hamcrest:hamcrest-library:$hamcrestVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.3")
}
