
# Use a Java base image
FROM openjdk:17


WORKDIR /app

# Copy the Spring Boot application JAR file into the Docker image
COPY target/bpi-app-api-*.jar bpi-app-api.jar

# Expose the port that the Spring Boot application is listening on
EXPOSE 8086
# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "bpi-app-api.jar"]






