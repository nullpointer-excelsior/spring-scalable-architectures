# Use openjdk 21 as the base image
FROM openjdk:21-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy Gradle wrapper and build.gradle files
COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy source code
COPY src src

# Run Gradle build to create the jar
RUN ./gradlew build --no-daemon

# Create a new image with only the built jar
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar


# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
