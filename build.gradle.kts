plugins {
        id("org.springframework.boot") version "3.0.1"
        id("io.spring.dependency-management") version "1.0.11.RELEASE"
        id("java")
        kotlin("jvm") version "1.9.24" // Kui kasutate Kotlinit
    }

group = "com.scrabble"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot starter sõltuvused
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")  // Kui kasutate andmebaasi
    implementation ("org.springframework.boot:spring-boot-starter-test")  // Testide jaoks
    implementation ("org.springframework.boot:spring-boot-starter-logging") // Logimine
    implementation ("org.springframework.boot:spring-boot-starter")  // Põhijooksu jaoks

    // PostgreSQL JDBC draiveri sõltuvus
    implementation ("org.postgresql:postgresql")

    // Testimise sõltuvused
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    // Hibernate sõltuvused (kui neid pole juba)
    implementation ("org.hibernate:hibernate-core:6.1.5.Final")
}

tasks.test {
    useJUnitPlatform() // See on oluline JUnit 5 jaoks
}

tasks.register<JavaExec>("run") {
    mainClass.set("org.example.Scrabble") // Siin määrate oma peamise klassi
    classpath = sourceSets.main.get().runtimeClasspath
}
tasks.bootRun {
    mainClass.set("org.example.ScrabbleApplication")  // Kontrollige, et siin on õige klassi nimi
}
