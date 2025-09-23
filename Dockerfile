# Используем образ Gradle с JDK
FROM gradle:8.10-jdk23

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем всё содержимое текущего каталога в /app
COPY /app .

# Собираем проект и исключаем выполнение тестов
RUN ./gradlew clean build -x test

# Установка переменных среды для управления памятью JVM
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0"

# Экспортируем порт
EXPOSE 7070

# Запуск приложения
CMD java $JAVA_OPTS -jar build/libs/app-1.0-SNAPSHOT-all.jar  # Убедитесь, что имя jar-файла верное

