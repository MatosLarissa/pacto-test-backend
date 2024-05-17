FROM maven:3.8.4-openjdk-8 AS build

COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

RUN mvn clean install

FROM openjdk:8-jdk-alpine

COPY --from=build /tmp/target/internal-recruitment-1.0.0-SNAPSHOT.jar /app/internal-recruitment.jar

ENTRYPOINT ["java","-jar","/app/internal-recruitment.jar"]
