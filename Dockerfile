FROM openjdk:8
WORKDIR /app
COPY target/spe.jar ./spe.jar

EXPOSE 8101



# Set the environment variables for the MySQL connection


# Start the Spring Boot application
CMD ["java", "-jar", "spe.jar"]

