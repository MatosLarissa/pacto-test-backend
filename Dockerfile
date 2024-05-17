FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y \
    openjdk-8-jdk \
    maven \
    && apt-get clean

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

COPY . /app

COPY init-db.sql /docker-entrypoint-initdb.d/

WORKDIR /app

RUN mvn clean install

FROM openjdk:8-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/internal-recruitment-1.0.0-SNAPSHOT.jar /app/internal-recruitment.jar

ENTRYPOINT ["java", "-jar", "/app/internal-recruitment.jar"]
