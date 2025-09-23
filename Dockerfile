# Базовый образ
FROM gradle:8.10-jdk23 AS builder

# Рабочая директория
WORKDIR /app

# Копируем файлы Gradle
COPY gradlew .
COPY gradle/wrapper/ gradle/wrapper/
COPY build.gradle settings.gradle .

# Копируем исходники
COPY src/ src/

# Собираем проект
RUN ./gradlew --no-daemon build -x test

# Второй этап - финальный образ
FROM eclipse-temurin:23-jdk

# Рабочая директория
WORKDIR /app

# Копируем собранный jar
COPY --from=builder /app/build/libs/*.jar ./app.jar

# Настройки JVM
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0"

# Запуск приложения
ENTRYPOINT ["java", "$JAVA_OPTS", "-jar", "app.jar"]
