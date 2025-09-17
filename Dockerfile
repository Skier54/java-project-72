FROM gradle:8.10-jdk23

WORKDIR /app

COPY /app .

RUN ["./gradlew", "clean", "build"]

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=60.0 -XX:InitialRAMPercentage=50.0"

CMD ["./gradlew", "run"]
