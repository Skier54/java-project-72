FROM gradle:8.10-jdk23
WORKDIR /app
COPY /app .
RUN ./gradlew clean build -x test
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0"
CMD ["./gradlew", "run"]
