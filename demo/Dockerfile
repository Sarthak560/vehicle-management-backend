# Use Java 21 base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (default Spring Boot port)
EXPOSE 8080

# Run the built Spring Boot JAR
CMD ["java", "-jar", "target/nextworld-0.0.1-SNAPSHOT.jar"]


