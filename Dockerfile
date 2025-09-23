FROM gradle:8.10-jdk17

WORKDIR /app

COPY /app .

RUN ["./gradlew", "clean", "build"]

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0"

EXPOSE 7070

CMD ["./gradlew", "run"]

