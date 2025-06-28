FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY target/*.jar app.jar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/app.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]