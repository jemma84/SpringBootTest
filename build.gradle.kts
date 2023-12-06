import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    java
    id("io.spring.dependency-management") version "1.1.3"
    id("org.springframework.boot") version "3.0.6"
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

version = "0.1.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.0.4")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test:5.6.1")
    testImplementation("org.testcontainers:postgresql:1.18.3")
    testImplementation("org.testcontainers:junit-jupiter:1.18.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.name}:${project.version}")
}
