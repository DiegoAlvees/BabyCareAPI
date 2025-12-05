FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test -x check

CMD ["java", "-jar", "build/libs/babycare-0.0.1-SNAPSHOT.jar"]
