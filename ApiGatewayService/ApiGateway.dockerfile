# Use a base Java image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file
COPY target/ApiGatewayService-0.0.1-SNAPSHOT.jar /app/api-gateway-service.jar

# Expose the necessary port
EXPOSE 8087

# Run the application
ENTRYPOINT ["java", "-jar", "api-gateway-service.jar"]
