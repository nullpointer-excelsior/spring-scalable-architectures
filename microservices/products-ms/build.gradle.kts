plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.benjamin.ecommerce"
version = "0.0.1-SNAPSHOT"

extra["springCloudVersion"] = "2024.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	// implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.postgresql:postgresql:42.7.5")
	implementation("io.micrometer:micrometer-registry-otlp")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
	implementation("io.zipkin.contrib.otel:encoder-brave:0.1.0")
	
	runtimeOnly("com.github.loki4j:loki-logback-appender:1.5.2")
	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.assertj:assertj-core:3.27.2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	compileOnly("org.projectlombok:lombok:1.18.36")
	annotationProcessor("org.projectlombok:lombok:1.18.36")

	implementation("org.mapstruct:mapstruct:1.6.3")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

	testCompileOnly("org.projectlombok:lombok:1.18.36")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.36")
	testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
