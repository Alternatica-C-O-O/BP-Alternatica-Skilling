### --- Fase de Construcción (Builder) ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY pom.xml mvnw ./
### Integrar lo puesto en el shared-libraries

COPY .mvn ./.mvn
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B -Dmaven.multiModuleProjectDirectory=.
COPY src ./src
RUN ./mvnw package -DskipTests

### --- Fase de Ejecución (Runner) ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]