FROM eclipse-temurin:23-jdk
WORKDIR /app

# Установите конкретную версию Gradle
RUN curl -fsSL https://services.gradle.org/distributions/gradle-8.10-bin.zip \
    -o gradle-8.10-bin.zip && \
    unzip gradle-8.10-bin.zip -d /opt/gradle && \
    rm gradle-8.10-bin.zip && \
    export PATH=$PATH:/opt/gradle/gradle-8.10/bin

COPY gradlew .
COPY gradle/wrapper/ gradle/wrapper/
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew
RUN ./gradlew dependencies

COPY src/ src/
RUN ./gradlew build -x test

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=60.0 -XX:InitialRAMPercentage=50.0"
ENTRYPOINT ["java", "$JAVA_OPTS", "-jar", "app.jar"]
