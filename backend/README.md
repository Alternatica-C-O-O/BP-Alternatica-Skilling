# Proyecto Backend Skilling LMS

Este repositorio contiene el código fuente de los microservicios que componen el backend del sistema LMS (Learning Management System) de Skilling. La arquitectura está diseñada para ser escalable y modular, utilizando tecnologías como Spring Boot, Spring Cloud, Eureka (Service Discovery), Config Server, API Gateway, Kafka y RabbitMQ.

## 1. Prerequisitos

Antes de empezar, asegúrate de tener instalados los siguientes programas en tu sistema:

* **Java Development Kit (JDK) 21 o superior:** Necesario para compilar y ejecutar las aplicaciones Spring Boot.
* **Apache Maven 3.8.x o superior:** Herramienta de automatización de construcción para compilar los proyectos Java.
* **Docker Desktop (incluye Docker Compose):** Esencial para ejecutar toda la arquitectura de microservicios de forma consistente y aislada en contenedores.

### Ejecución del Proyecto

Para poder crear y probar de forma local el proyecto backend, primero debes generar los archivos ejecutables (JARs) de cada microservicio. Navega a la raíz del proyecto donde se encuentran las carpetas de los microservicios (ej., `users-service`, `config-server`, etc.) y ejecuta el siguiente comando Maven en cada uno:

```bash
# Ejemplo para users-service. Repite para cada microservicio.
cd users-service
mvn clean install
cd .. # Regresa a la raíz del proyecto
```
Una vez que todos los JARs estén generados, puedes levantar toda la arquitectura de microservicios utilizando Docker Compose. Esto asegura un entorno de ejecución limpio y con todos los servicios interconectados (Eureka, Config Server, RabbitMQ, Kafka, etc.).

Desde la raíz del proyecto (donde se encuentra el archivo ```docker-compose.yml```), ejecuta:

```bash
docker compose up --build
```
* El flag ```--build``` es importante la primera vez y cada vez que realices cambios en el código de tus microservicios, ya que reconstruye las imágenes de Docker con los últimos JARs generados.
* Para detener y limpiar los contenedores, usa ```docker compose down```.

### Uso del Servicio Kafka
Si necesitas interactuar directamente con el contenedor de Kafka (por ejemplo, para ejecutar herramientas de línea de comandos o crear temas), puedes acceder a su shell usando ```docker exec```.

Primero, identifica el nombre exacto de tu contenedor de Kafka (generalmente es ```backend-kafka-1``` si el nombre de tu proyecto es ```backend``` por defecto):

```bash
docker ps
```

Luego, accede a su shell:
```bash
docker exec -it backend-kafka-1 bash # O sh si bash no está disponible
```

## 2. Acceso a las Interfaces de Gestión
Una vez que los servicios estén en ejecución, puedes acceder a las siguientes interfaces a través de tu navegador:

* **Eureka Server (Service Discovery):** ```http://localhost:8761```
* **Spring Cloud Config Server:** ```http://localhost:8888```
* **API Gateway:** ```http://localhost:8080``` (punto de entrada a tus microservicios, ej. ```http://localhost:8080/users-service/...```)
* **Kafka UI (si está habilitado):** ```http://localhost:9021``` (si usas Control Center o similar)

## 3. Resolución de Problemas Comunes
"Connection refused" o servicios que no arrancan:

* Asegúrate de que todos los contenedores ```config-server``` y ```service-discovery``` estén ```(healthy)``` antes de que los demás microservicios intenten conectar. Usa ```docker compose ps```.
* Verifica que no tengas otras aplicaciones usando los puertos ```8761```, ```8888```, ```8080```, etc., en tu máquina local.
* Si realizaste cambios en el código de los microservicios Java, asegúrate de haber ejecutado ```mvn clean install``` y luego ```docker compose up --build``` de nuevo.
* Revisa los logs específicos de cada servicio con ```docker compose logs -f <nombre_del_servicio>``` (ej., ```docker compose logs -f users-service```).

Problemas de caché de Docker: Si los cambios en el código no se reflejan, intenta reconstruir las imágenes sin caché:

```bash
docker compose build --no-cache
docker compose up
```