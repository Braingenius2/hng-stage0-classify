# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copy the wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (this caches them so builds are faster)
RUN ./mvnw dependency:go-offline -B

# Copy the actual source code
COPY src src

# Build the JAR file
RUN ./mvnw package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy only the compiled JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Railway will map this automatically)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
