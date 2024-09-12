# Use a base Java image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file
COPY target/EurekaServerService-0.0.1-SNAPSHOT.jar /app/eureka-server-service.jar

# Expose the necessary port
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "eureka-server-service.jar"]
