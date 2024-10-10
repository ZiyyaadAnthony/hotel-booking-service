# Use a Maven image as a base
FROM maven:3.8.5-openjdk-17 AS build
# Set the working directory
WORKDIR /app
# Copy the pom.xml and other necessary files to the container
COPY pom.xml .
COPY src ./src
# Package the application
RUN mvn clean package -DskipTests
# Use a lightweight image for running the application
FROM openjdk:17-jdk-slim
# Set the working directory
WORKDIR /app
# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]