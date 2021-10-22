plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.21"
    id("org.jetbrains.kotlin.kapt") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("io.micronaut.application") version "2.0.6"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.21"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.5.21"
}

version = "0.1"
group = "com.example"
allOpen {
    annotation("javax.persistence.Entity")
    annotation("jakarta.inject.Singleton")
}
noArg {
    annotation("javax.persistence.Entity")
//    annotation("jakarta.inject.Singleton")
}


val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.ktor:ktor-jackson")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.kotlin:micronaut-ktor")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

    implementation("io.micronaut.sql:micronaut-hibernate-jpa")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("org.postgresql:postgresql:42.3.0")

    runtimeOnly("com.h2database:h2")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}


application {
    mainClass.set("com.example.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }


}
