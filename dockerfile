# Build stage
FROM maven:3.8.6-amazoncorretto-17 AS build
WORKDIR .
COPY pom.xml .
COPY src ./src
COPY src/main/resources ./src/main/resources
RUN mvn package

# Final stage
FROM openjdk:17-jdk-alpine
COPY --from=build /target/christian-1.0.0.jar /christian-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/christian-1.0.0.jar"]