# Use an official Maven image with JDK 17
FROM maven:3.9.4-eclipse-temurin-17

# Set the working directory in the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the project (skip tests if desired)
RUN mvn clean package -DskipTests

# Expose the port (Render will pass a PORT environment variable)
EXPOSE 8080

# Run the application (ensure the JAR name matches your built artifact)
CMD ["java", "-jar", "target/insurance-api-0.0.1-SNAPSHOT.jar"]
