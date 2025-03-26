# Simplify Money Insurance API

A Spring Boot-based Java API for managing insurance purchases. This application provides endpoints to list all available insurances, simulate the purchase of an insurance policy, and download a dummy policy document. Additionally, a bonus feature includes curated insurance recommendations based on user details like age, gender, and income.

## Table of Contents

- [Features](#features)
- [Project Overview](#project-overview)
- [API Endpoints](#api-endpoints)
- [Build & Run Instructions (Local)](#build--run-instructions-local)
- [Deployment on Render](#deployment-on-render)
- [Testing the Endpoints](#testing-the-endpoints)
- [Unit Tests](#unit-tests)
- [Dependencies and Environment](#dependencies-and-environment)
- [Additional Notes](#additional-notes)

## Features

- **Get All Insurances:** List all available insurance policies.
- **Purchase an Insurance:** Simulate a successful purchase and return a purchase receipt.
- **Download Policy Document:** Download a dummy PDF file as a policy document.
- **Bonus:** Curated insurance recommendations based on user details (age, gender, income).

## Project Overview

This API is developed using Spring Boot, JPA, and PostgreSQL. It uses a Dockerfile for containerization and is deployed on Render. The external PostgreSQL database is used for persistent storage, and sample data is populated via a `data.sql` script.

## API Endpoints

1. **Get All Insurances**  
   - **URL:** `/api/insurance`  
   - **Method:** `GET`  
   - **Response:** JSON array containing insurance records.

2. **Purchase an Insurance**  
   - **URL:** `/api/insurance/purchase/{id}`  
   - **Method:** `POST`  
   - **Response:** A text message confirming the purchase.  
   - **Example:** Purchase insurance with ID 1.

3. **Download Policy Document**  
   - **URL:** `/api/insurance/download-policy`  
   - **Method:** `GET`  
   - **Response:** A dummy PDF file download.

## Build & Run Instructions (Local)

### Prerequisites
- Java JDK 17 (or your target version)
- Maven

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/sriteja-28/simplify-money-insurance-api.git
   cd simplify-money-insurance-api
Build the Application

mvn clean package
Run the Application
      or 
In spring tool suite Just run it 

java -jar target/insurance-api-0.0.1-SNAPSHOT.jar
      or 
In spring tool suite Just run it


Access the Endpoints Locally

Get All Insurances:
Visit: http://localhost:8080/api/insurance

Purchase Insurance:
curl -X POST http://localhost:8080/api/insurance/purchase/1

Download Policy Document:
curl -O http://localhost:8080/api/insurance/download-policy

Deployment on Render
Dockerfile
The application is containerized using the following Dockerfile (located in the project root):

dockerfile
# Use an official Maven image with JDK 17 (using Eclipse Temurin)
FROM maven:3.9.4-eclipse-temurin-17

# Set the working directory in the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the project (skip tests if desired)
RUN mvn clean package -DskipTests

# Expose the port (Render sets a PORT env variable)
EXPOSE 8080

# Run the application (ensure the JAR name matches your built artifact)
CMD ["java", "-jar", "target/insurance-api-0.0.1-SNAPSHOT.jar"]
External PostgreSQL Configuration
In src/main/resources/application.properties, configure the external PostgreSQL database (provided by Render):

properties
spring.datasource.url=jdbc:postgresql://dpg-cvi36pfnoe9s73aqs560-a.oregon-postgres.render.com:5432/simplify_money_insurance_api
spring.datasource.username=simplify_money_insurance_api_user
spring.datasource.password=BsUskNCt8u8RRYd1H5DhOxzII1d39MdB
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

# Ensure your application listens on the PORT provided by Render
server.port=${PORT:8080}
Deployment Steps on Render
Push your code to GitHub:
Make sure your repository is public and includes all source files, Dockerfile, README, and data.sql.

Create a New Web Service on Render:

Log in to Render.com and select "New Web Service".

Connect your GitHub repository.

Select the Docker environment (Render will detect the Dockerfile).

Render will automatically build and deploy your application.

Once deployed, your app will be accessible at a public URL (e.g., https://simplify-money-insurance-api.onrender.com).

Testing the Endpoints
Using cURL
-------------------------------------
Get All Insurances:
curl -X GET https://simplify-money-insurance-api.onrender.com/api/insurance

Purchase an Insurance (ID = 1):
curl -X POST https://simplify-money-insurance-api.onrender.com/api/insurance/purchase/1

Download Policy Document:
curl -O https://simplify-money-insurance-api.onrender.com/api/insurance/download-policy

Using Postman
Open Postman.

Create new requests for each endpoint.

Set the appropriate HTTP method (GET for listing/downloading, POST for purchasing).

Enter the public URL endpoints.

Send the requests and verify the responses.

Unit Tests
Unit tests are located under src/test/java.

To run the tests locally, execute:
mvn test
Tests validate key functionalities like listing insurances and purchasing a policy.

Dependencies and Environment
Java: JDK 17 (or as configured)

Build Tool: Maven

Database: PostgreSQL (external on Render) configured in application.properties

Frameworks: Spring Boot, Spring Data JPA

Containerization: Docker

Additional Notes
Data Initialization:
The data.sql file in src/main/resources is executed at startup to populate the INSURANCE table with sample data.

Bonus Feature:
Curated insurance recommendations based on user details have been implemented as an additional endpoint (refer to the source code for details).

Deployment:
For production deployments, using an external PostgreSQL database is recommended for data persistence.

Known Challenges:
-------------------------
Ensuring the external database is properly connected.
Handling file-based policy documents (currently a dummy PDF file in resources).

Contact
For any questions or further information, please contact:

Muthangi Sri Teja
Email: sritejamuthangi336@gmail.com


---
This README.md provides a comprehensive guide covering local setup, deployment on Render, API endpoints with sample cURL commands, and additional documentation regarding your project. Feel free to adjust or expand upon any section as needed for your submission.







