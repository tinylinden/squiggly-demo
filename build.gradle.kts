import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    groovy
    id("com.coditory.integration-test") version "1.2.1"
    id("org.openapi.generator") version "5.3.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

extra["groovyVersion"] = "3.0.8"
extra["cucumberVersion"] = "6.11.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.github.bohnman:squiggly-filter-jackson:1.3.18")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("javax.validation:validation-api:2.0.0.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.skyscreamer:jsonassert:1.5.0")
    testImplementation("com.github.groovy-wslite:groovy-wslite:1.1.3")
    // spock
    testImplementation("org.spockframework:spock-core")
    testImplementation("org.spockframework:spock-junit4")
    testImplementation("org.codehaus.groovy:groovy:${property("groovyVersion")}")
    // cucumber
    testImplementation("org.junit.vintage:junit-vintage-engine")
    testImplementation("io.cucumber:cucumber-java:${property("cucumberVersion")}")
    testImplementation("io.cucumber:cucumber-junit:${property("cucumberVersion")}")
    testImplementation("io.cucumber:cucumber-spring:${property("cucumberVersion")}")
}

dependencyManagement {
    imports {
        mavenBom("org.spockframework:spock-bom:2.0-M4-groovy-3.0")
    }
}

tasks.withType<KotlinCompile> {
    dependsOn("openApiGenerate")
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(FAILED, SKIPPED)
        exceptionFormat = FULL
    }
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("$buildDir/generated/sources/openApiGenerate/kotlin/main")
    }
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/src/main/resources/static/public/specs/squiggly-demo-api.v1.yaml")
    outputDir.set("$buildDir/generated/sources/openApiGenerate/kotlin/main")
    ignoreFileOverride.set("$rootDir/.openapi-generator-ignore")
    skipOperationExample.set(true)
    apiPackage.set("pl.tinylinden.squigglydemo.adapters.rest.v1")
    modelPackage.set("pl.tinylinden.squigglydemo.adapters.rest.v1.model")
    modelNameSuffix.set("Dto")
    additionalProperties.set(
        mapOf(
            "exceptionHandler" to "false",
            "interfaceOnly" to "true",
            "sourceFolder" to "/"
        )
    )
}
