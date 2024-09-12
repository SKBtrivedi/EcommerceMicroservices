# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/ProductDetailService-0.0.1-SNAPSHOT.jar /app/product-detail-service.jar

# Expose the application port
EXPOSE 8086

# Run the application
ENTRYPOINT ["java", "-jar", "product-detail-service.jar"]
