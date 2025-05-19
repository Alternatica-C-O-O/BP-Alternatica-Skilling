### --- Fase de Construcción (Builder) ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copiar los archivos pom.xml y el wrapper
COPY pom.xml mvnw ./
COPY .mvn ./.mvn

# Dar permisos de ejecución al wrapper de Maven
RUN chmod +x ./mvnw

# Descarga las dependencias de Maven
RUN ./mvnw dependency:go-offline -B -Dmaven.multiModuleProjectDirectory=.

# Copia el código fuente
COPY src ./src

# Construye la aplicación Spring Boot
RUN ./mvnw package -DskipTests

### --- Fase de Ejecución (Runner) ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia el JAR construido de la fase anterior
COPY --from=builder /backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
