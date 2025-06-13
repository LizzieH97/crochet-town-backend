# Start from a Java runtime base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy your built jar and dependency files
COPY target/crochet-town-backend-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
