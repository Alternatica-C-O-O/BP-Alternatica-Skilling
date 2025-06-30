= Microservicios Individuales
Se han definido los siguientes microservicios dentro del proyecto de documentación de API, los cuales se pueden categorizar de la siguiente manera, reflejando una arquitectura modular y escalable para el LMS:

== Configuración de la Infraestructura
Esta sección agrupa los microservicios y componentes fundamentales que sustentan el funcionamiento de la plataforma, manejando aspectos transversales y no directamente relacionados con la lógica de negocio principal de una entidad.

- *gateway-service* (API Gateway): Actúa como el punto de entrada unificado para todas las solicitudes externas al LMS. Se encarga del enrutamiento de peticiones a los microservicios correspondientes, la autenticación y autorización preliminar (delegando a *auth-service*), *rate limiting*, *load balancing* básico y el mapeo de API amigables a rutas internas de los servicios.

- *discovery-service* (Eureka Server): Permite a los microservicios registrarse y descubrir la ubicación de otros servicios dinámicamente. Es crucial para una arquitectura distribuida donde las instancias de servicio pueden escalar o fallar. Siendo que los servicios se registran al iniciar y consultan este servicio para encontrar dependencias sin necesidad de configuraciones estáticas de IP/puerto.

- *config-service* (Configuration Managment): Centraliza la gestión de la configuración de todos los microservicios. Permite actualizar propiedades de la aplicación sin necesidad de reconstruir o redeplejar los servicios. Las configuraciones se almacenan en un repositorio Git y los servicios las obtienen al inicio o las refrescan dinámicamente.

== Servicios Esenciales de Dominio 
Estos microservicios encapsulan la lógica de negocio central y fundamental del Sistema de Gestión del Aprendizaje (LMS), representando las funcionalidades directamente relacionadas con el proceso educativo y los actores principales:

- *users-service*: Gestión de usuarios (estudiantes, docentes, administradores). Incluye registro, autenticación, autorización (gestión de roles y permisos), perfiles de usuario y recuperación de credenciales. Este por defecto debe estar relacionado con PostgreSQL para datos transaccionales de usuario. Puede integrar Redis para sesiones de usuario y *token management*

- *content-service*: Administra la creación, almacenamiento, recuperación y organización de todo el contenido educativo (cursos, módulos, lecciones, recursos didácticos). El cual, junto con *users-service*, debe integrarse con PostgreSQL para la estructura relacional del contenido. Almacenamiento de archivos (videos, documentos) en un sistema de almacenamiento de objetos (ej., S3, Google Cloud Storage). MongoDB para metadatos flexibles y *logs* de contenido.

- *curriculum-service*: Define y gestiona la estructura curricular del LMS, incluyendo planes de estudio, perfiles curriculares, competencias y sus relaciones. Debe estar relacionado con PostgreSQL para la persistencia de las relaciones complejas y la integridad de los datos curriculares.

- *enrollment-service*: Maneja el ciclo de vida de la inscripción de usuarios en cursos, programas o eventos. Incluye validación de prerrequisitos, gestión de cupos y seguimiento del estado de la inscripción. Por lo que, debe ser gestionado por PostgreSQL para mantener la consistencia transaccional de las inscripciones y sus estados.

- *assessment-service*: Gestiona la creación, aplicación, calificación y almacenamiento de evaluaciones (exámenes, tareas, *quizzes*). Proporciona APIs para el envío de trabajos, rúbricas y *feedback*. Este de la misma forma, debe estar gestionado por PostgreSQL para la lógica transaccional de calificaciones y asignaciones.

== Servicios Dependientes y de Integración
Estos servicios extienden la funcionalidad principal o actúan como puentes con sistemas externos o componentes especializados.

- *notifications-service*: Centraliza la lógica para generar y enviar notificaciones a los usuarios a través de diversos canales (*email*, SMS, notificaciones *push in-app*). Gestiona plantillas y el historial de notificaciones. Este debe estar gestionado por PostgreSQL para plantillas y historial de notificaciones. Puede usar un servicio de mensajería (ej., Kafka) para desacoplar el envío y Redis para Pub/Sub en notificaciones en tiempo real.

- *reporting-service*: Genera informes analíticos y paneles de control basados en los datos recopilados de otros servicios (ej., progreso de estudiantes, rendimiento de cursos, uso de la plataforma). Este debe estar conectado a PostgreSQL para datos transaccionales, MongoDB para *logs* e InfluxDB para métricas de series de tiempo. Podría utilizar una base de datos analítica dedicada (ej., ClickHouse) para reportes muy complejos si fuera necesario.

- *financial-service:*: Gestiona todas las operaciones económicas relacionadas con el LMS, incluyendo la administración de planes de precios, procesamiento de pagos y registro de transacciones. Este debe ser gestionado por PostgreSQL para mantener la integridad transaccional de los pagos y las inscripciones financieras. Se integra con pasarelas de pago externas. MongoDB para almacenar *payloads raw* de transacciones.

== Servicios Secundarios-Transaccionales
Estos microservicios manejan funcionalidades de soporte que, aunque no son el *core* del dominio, implican transacciones y son esenciales para una experiencia de usuario completa y robusta.

- *resource-planning-service*: Se enfoca en la asignación dinámica y la gestión de la disponibilidad de recursos temporales o compartidos, como horarios de clases, asignación de laboratorios virtuales o físicos, y disponibilidad de equipos especiales para los cursos. Este debe ser gestionado por PostgreSQL para la gestión de reservas y horarios, garantizando la consistencia y evitando conflictos de recursos.

- *communication-service*: Proporciona funcionalidades para la interacción y comunicación dentro del LMS, como foros de discusión, mensajería directa entre usuarios y grupos, y sistemas de anuncios. Este debe ser gestionado por PostgreSQL para datos estructurados de foros y mensajes, y potencialmente MongoDB para almacenar el contenido de mensajes o publicaciones con formatos más flexibles si se requiriera. Puede usar WebSockets para *chat* en tiempo real.

- *analytics-service*: Recopila, procesa y analiza datos sobre el comportamiento del usuario para extraer *insights* sobre patrones de aprendizaje, *engagement* y efectividad del contenido. A través del uso de InfluxDB para datos de series de tiempo de actividad de usuario. Podría usar un *data lake* (ej., S3) para almacenar datos *raw* y un motor de procesamiento (ej., Spark) para análisis complejos.

#pagebreak()