FROM gradle:8.10-jdk23

WORKDIR /app

COPY app/gradle gradle
COPY app/build.gradle.kts .
COPY app/settings.gradle.kts .
COPY app/gradlew .

RUN ./gradlew --no-daemon dependencies

COPY app/src src
COPY app/config config

RUN ./gradlew --no-daemon build
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0"
EXPOSE 7070
CMD ["./gradlew", "run"]
