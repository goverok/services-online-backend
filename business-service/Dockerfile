# === Стадия 1: сборка проекта с Gradle ===
FROM gradle:8.13-jdk17 AS builder

WORKDIR /app

# 1. Скопировать только файлы конфигурации (не исходники), чтобы кэшировать зависимости
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle gradle

# 2. Кэш зависимостей (не будет перекачивать их при каждом билде)
RUN ./gradlew --no-daemon dependencies

# 3. Скопировать исходники
COPY src src

# 4. Сборка jar
RUN ./gradlew clean bootJar --no-daemon


# === Стадия 2: минимальный рантайм с JAR ===
FROM eclipse-temurin:17-jre-ubi9-minimal

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]