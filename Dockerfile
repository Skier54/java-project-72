FROM gradle:8.10-jdk23

WORKDIR /app

RUN ./gradlew --no-daemon dependencies

COPY /app .

RUN ./gradlew --no-daemon build -x test

ENV JAVA_OPTS "-Xmx512M -Xms512M"

EXPOSE 7070

CMD ["./gradlew", "run"]


