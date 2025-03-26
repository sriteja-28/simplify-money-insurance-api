# Use an official Maven image with JDK 17 (updated tag)
FROM maven:3.8.7-openjdk-17

# Set the working directory in the container
WORKDIR /app

# Copy your project files into the container
COPY . .

# Build your project (skip tests if desired)
RUN mvn clean package -DskipTests

# Expose the port (Render uses the PORT env variable; your app should use it as well)
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "target/insurance-api-0.0.1-SNAPSHOT.jar"]

