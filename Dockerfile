# Stage 1: Build the JAR
FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Create the Docker Image
FROM openjdk:11
WORKDIR /app
COPY --from=build /app/target/eventsProject-1.0.0.jar eventsProject-1.0.0.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "eventsProject-1.0.0.jar"]
