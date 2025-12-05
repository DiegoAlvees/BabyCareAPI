FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# Dá permissão de execução ao gradlew
RUN chmod +x gradlew

# Agora o build funciona
RUN ./gradlew clean build -x test -x check

CMD ["java", "-jar", "build/libs/babycare-0.0.1-SNAPSHOT.jar"]
