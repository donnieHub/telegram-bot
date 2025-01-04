FROM gradle:8.12.0-jdk21 as builder
LABEL authors="mychko"

WORKDIR /app
COPY . /app

RUN ./gradlew --no-daemon :app:dependencies
RUN ./gradlew --no-daemon :app:clean
CMD ["sh", "-c", "./gradlew --no-daemon :app:clean && ./gradlew --no-daemon :app:build"]