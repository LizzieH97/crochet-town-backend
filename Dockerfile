


# syntax=docker/dockerfile:1

FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Now run the jar in a lightweight image
FROM eclipse-temurin:17-jdk
WORKDIR /app


COPY --from=build /app/target/app.jar app.jar



EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]




