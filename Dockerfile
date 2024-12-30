FROM gradle:8.12.0-jdk11-alpine
LABEL authors="mychko"

WORKDIR /app
COPY . /app
RUN ./gradlew run