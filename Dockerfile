# Use a base image with a Java Development Kit (JDK)
FROM eclipse-temurin:17-jre-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files (pom.xml, src)
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Use a smaller base image for the final production image
FROM openjdk:17-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot application listens on (default is 8080)
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]