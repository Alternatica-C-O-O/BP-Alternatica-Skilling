= Base de Datos y Persistencia
Tras la definición de la arquitectura planteada, se debe dar por hecha la necesidad de persistencia de datos, lo cual permitirá que la arquitectura del Sistema de Gestión del Aprendizaje (LMS) garantice la integridad, disponibilidad y durabilidad de la información crítica. De esta manera, un diseño de base de datos robusto y correctamente relacionado permitirá soportar la complejidad de los datos académicos, desde perfiles de usuario y contenidos de curso hasta registros de calificaciones y actividades de auditoría. Por lo que, la elección de una estrategia de persistencia adecuada impactará directamente en el rendimiento y la escalabilidad del sistema, además de su flexibilidad para adaptarse a futuros requisitos y volúmenes de datos. De esta manera, este debe ser un paso clave para transformar los modelos conceptuales del sistema en una realidad operativa que pueda almacenar y recuperar eficientemente todos los datos generados por la interacción entre usuarios y funcionalidades.

En definición, lo que se espera con el diseño de la DB es poder reflejar directamente la estructura de clases UML previamente definida, asegurando una correspondencia lógica entre el modelo de objetos del *backend* y el esquema de almacenamiento de datos. Cada entidad clave, como USUARIO, CURSO\_OFERTADO, EVALUACION o INSCRIPCION, se mapeará a tablas o colecciones específicas, con sus atributos representados como columnas o campos y sus relaciones traducidas en claves foráneas o referencias. De esta manera, se debe lograr tener una gran coherencia entre los diagramas de clases y el diseño de la base de datos para lograr una implementación sinérgica, facilitación del desarrollo del *Object-Relational Mapping* (ORM) y del mecanismo de persistencia elegido; lo que permitirá minimizar las complejidades en la manipulación de datos a nivel de aplicación.

== Estrategia de Persistencia Políglota
Como primera estrategia planteada y en base a la arquitectura que se quiere seguir y los servicios que se quieren utilizar, se considera poder utilizar una estrategia de persistencia políglota adoptada para que el LMS reconozca que no existe una única solución de base de datos óptima para todas las necesidades de almacenamiento y recuperación de datos dentro de un sistema complejo. Esta aproximación implica el uso de múltiples tipos de bases de datos, cada una seleccionada específicamente por su idoneidad para un conjunto particular de datos o un patrón de acceso. Esto permite maximizar el rendimiento, la escalabilidad y la eficiencia de costos para distintas funcionalidades. Por ejemplo, mientras una base de datos relacional puede ser ideal para la información transaccional y fuertemente estructurada (como inscripciones y calificaciones), una base de datos NoSQL podría ser superior para el almacenamiento de contenido no estructurado o semi-estructurado (como *logs* de actividad o metadatos de recursos didácticos) o para manejar grandes volúmenes de eventos en tiempo real.

A pesar de comprender este principal aspecto, podemos mencionar que es necesario poder diversificar el almacenamiento de datos para la eficiencia técnica y la flexibilidad arquitectónica. Al no depender de una única tecnología, el sistema puede evolucionar y adaptarse más fácilmente a futuros cambios en los requisitos de datos o a la aparición de nuevas tecnologías de bases de datos. Permite a los equipos de desarrollo elegir la "herramienta adecuada para el trabajo adecuado", evitando compromisos de diseño que surgirían al forzar todos los tipos de datos en un solo paradigma. Además, una estrategia políglota puede mejorar la resiliencia general del sistema, ya que la falla en un tipo de base de datos no necesariamente impactaría a la totalidad de los datos o funcionalidades.

En definitiva, podemos entender que para el uso productivo y la mejora de resiliencia arquitectónica, se debe emplear como principal fuente el uso de una base de datos SQL reactiva, la cual pueda manejar las relaciones que existen entre diversas entidades, las cuales hemos definido dentro del diagrama de clases anteriormente desarrollado. De esta manera, lo que se pretende es poder implementar una persistencia que requerirá una cuidadosa orquestación y diseño de la integración entre otras diferentes bases de datos futuras. Como paso presentado, podemos mencionar la implicación para definir las responsabilidades de cada base de datos a través de la elección de *caching*, NoSQL que se podrían interrelacionar con la base de datos SQL reactiva propuesta. De esta manera, se buscará poder establecer mecanismos eficientes para la sincronización o replicación de datos cuando sea necesario, y desarrollar una capa de acceso a datos que abstraiga la complejidad subyacente para los servicios de la aplicación. La elección específica de cada tecnología de base de datos se basará en un análisis detallado de los patrones de acceso, volumetría, la naturaleza de la estructura de los datos y los requisitos de consistencia para cada subdominio del LMS.

== Base de Datos Principal

#figure(
  image("../../src/images/DB Diagram.png",width: 38.7em),
  caption: "Diagrama de Entidad-Relación de la Base de Datos Principal. Nota. Este diagrama ilustra la estructura de la base de datos relacional principal del LMS, mostrando las tablas, sus atributos y las relaciones entre ellas para garantizar la integridad y coherencia de los datos. Elaboración propia."
)

== Base de Datos Específicas
La selección de bases de datos específicas para el LMS obedece a una estrategia de persistencia políglota (justificada anteriroemtne por la alta cantidad de demanda de reucrsos que tendrá el sistema), reconociendo que cada tipo de dato y patrón de acceso requiere una solución de almacenamiento optimizada. Por lo que, esta aproximación no solo busca la máxima eficiencia y rendimiento, sino también la escalabilidad y resiliencia del sistema frente a volúmenes de datos crecientes y diversas demandas operacionales. De esta manera, a traǘes de un análisis volumérico relativo a los proceso observaodsy a nálisis MEFI y EFI obtenidos, se justifican las elecciones para el manejo de datos no estructurados y logs, así como para caching y sesiones, incluyendo un análisis volumétrico estimado basado en un entorno educativo típico.

=== Base de Datos MongoDB (NoSQL) para Datos No Estructurados y Logs
MongoDB, una base de datos NoSQL orientada a documentos, fue seleccionada por su flexibilidad inherente en el esquema de datos, su alta escalabilidad horizontal y su idoneidad para manejar grandes volúmenes de datos semi-estructurados o no estructurados. Es particularmente eficiente para escenarios donde los esquemas evolucionan con frecuencia (como los detalles de eventos en *logs*) o donde la estructura de los datos puede variar entre documentos. Esta base de datos será ideal para poder brindar:

- Los eventos de usuario (inicios de sesión, accesos a recursos, envíos de tareas) pueden tener una estructura variable. MongoDB permite almacenar cada log como un documento con sus campos específicos sin necesidad de predefinir un esquema rígido, facilitando la ingesta rápida y flexible.

- La información adicional sobre un recurso (etiquetas, idioma, autoría compleja, detalles de derechos de autor) puede variar enormemente entre diferentes tipos de recursos. MongoDB maneja esta diversidad de metadatos de forma eficiente.

- Almacenar el payload completo de webhooks de pasarelas de pago. Estos payloads son JSON de estructura variable y no siempre necesitan ser normalizados en un esquema relacional inmediato para su análisis posterior.

#figure(
  image("../../src/images/No Estructurados y Logs.png"),
  caption: "Diagrama de Colecciones de MongoDB para Datos No Estructurados y Logs. Nota. Este diagrama ilustra la estructura de las colecciones en MongoDB, diseñada para el almacenamiento flexible. Elaboración propia."
)

De esta manera, considerando que un LMS para una institución educativa superior puede albergar a más de 50,000 estudiantes activos intersedes, 5,000 docentes y administradores. Teniendo un promedio de 20 interacciones de actividad por usuario al día, 10,000 recursos didácticos, 500,000 notificaciones generadas al mes y 10,000 transacciones de pago al mes, esto generará un fuerte volumen en el que:

- *Esquema "log\_actividad"*: Tendrá un volumen diario de (55,000 usuarios) \* (20 logs/usuario), lo que sería cerca de 1,100,000 logs/día. Siendo que un tamaño estimado de 1KB por documento (incluyendo metadatos), se generaría 1.1 GB/día; lo que se traduce en \~400GB/año. Dada una estimación a 5 años (con crecimiento del 20% anual), se podría esperar alcanzar varios TB de datos de *logs*; siendo que en este aspecto, Mongo es muy robusto debido a su escalamiento horizontal.

- *Esquema "recursos\_didacticos\_metadata"*: Se estima un volumen inicial de 10,000 recursos, en el que cada documento tendrá en promedio un tamaño estimado de 5 KB (metadatos enriquecidos), lo que puede generar 50 MB de datos totales, siendo este un crecimiento a partir de la adición de nuevos recursos.

- *Esquema "notificaciones\_enviadas"*: Se estima que en un volumen mensual se puede aproximar a 500,000 notificaciones, siendo un tamaño de 2KB por documento, generará 1GB de datos mensuales, siendo 12GB/año, lo que en 5 años serán centenares de GB.

- *Esquema "transacciones\_pago\_raw"*: Se estima un volumen mensual de 10,000 transacciones, de tamaño de documento de 10 KB (*payloads* grandes), lo que mensualmente sería 100MB/mes y en un año 1.2GB/año.

=== Base de Datos Redis (Caching/Almacenamiento en Memoria)
Redis es un almacén de datos en memoria de código abierto, utilizado como base de datos, caché y *message broker*. Fue elegido por su velocidad (debido a que opera en RAM), su soporte para diversas estructuras de datos (cadenas, *hashes*, listas, conjuntos, etc.) y su capacidad para operar como un caché distribuido. Esto permite:

- Almacenar objetos completos o partes de ellos (ej. perfiles de usuario, detalles de cursos populares, estado de inscripción actual) que se leen repetidamente desde la base de datos principal. Esto reduce drásticamente la latencia de las solicitudes y la carga sobre PostgreSQL.

- Almacenar *tokens* de sesión (JWT) y la información de la sesión en Redis, para permitir una autenticación y autorización rápidas, mejorando la experiencia del usuario y la capacidad de respuesta de la aplicación. Es esencial para la RNF1.1: Tiempos de Respuesta.

- Implementar contadores para limitar la cantidad de solicitudes que un usuario o una IP pueden hacer en un período de tiempo, protegiendo el sistema de abusos y ataques.

#figure(
  image("../../src/images/Caching y Memoria.png"),
  caption: "Diagrama Conceptual de Redis para Caching y Almacenamiento en Memoria. Nota. Este diagrama ilustra cómo Redis se utiliza como una capa de caché en memoria para almacenar datos frecuentemente accedidos, tokens de sesión y contadores de rate limiting. Elaboración propia."
)

En favor de estos puntos, se puede lograr tener una tecnología para cumplir con los requisitos de rendimiento y disponibilidad, al reducir la dependencia directa de la base de datos principal para cada lectura. De esta manera, para medir la volumetría, esta reside en memoria en un momento dado, lo que permite mejorar la tasa de operaciones por segundo (IOPS)#footnote[Mide la cantidad de operaciones de entrada/salida (lecturas o escrituras) que un dispositivo de almacenamiento puede realizar por segundo. Es una métrica clave para el rendimiento de almacenamiento, especialmente en cargas de trabajo que requieren muchas transacciones pequeñas y aleatorias.].

- *Cache "objetos"*: Asumiendo un 10% de 55,000 usuarios activos (5,500) perfiles de caché, se puede estimar un tamaño por perfil de 1KB/perfil (JSON). Además, asumiendo 1,000 cursos populares en caché, se puede estimar un tamaño de 5KB/curso (JSON), lo cual, juntamente con 20,000 estados activos en caché (usuarios en cursos populares), se puede tener un tamaño por estado estimado de 0.5KB/estado. Siendo en total un volumen de \~20.5MB.

- *Cache "sesiones de usuario"*: De acuerdo con RNF1.2, el sistema debe soportar al menos 5,500 usuarios concurrentes. Siendo que un estimado de tamaño por sesión de 0.5KB (*token* JWT + información de sesión). Siendo que se puede definir un volumen total de \~2.75MB, siendo un consumo muy bajo de memoria.

- *Cache "Rate Limiting"*: Los contenedores de *rate limiting* son muy pequeños (ej. un entero por IP/usuario) y ocupan memoria insignificante.

- *Cache "Pub/Sub"*: El modelo Pub/Sub de Redis no almacena los mensajes de forma persistente a menos que se configure con persistencia (RDB/AOF), sino que los transfiere; de esta manera, se logra tener un impacto menor en el uso de CPU y red más que en almacenamiento persistente.

=== Base de Datos InfluxDB (Series de Tiempo)
InfluxDB es una base de datos de series de tiempo de alto rendimiento, diseñada específicamente para manejar grandes volúmenes de datos con una componente temporal. Su arquitectura está optimizada para la ingesta rápida, el almacenamiento eficiente y la consulta ágil de datos indexados por tiempo. Fue seleccionada por su idoneidad para casos de uso que requieren un análisis profundo de cómo los valores cambian a lo largo del tiempo, lo cual permitirá el monitoreo del sistema y la comprensión del comportamiento del usuario en un LMS. Esto permite:

- La secuencialidad y repetitividad, lo que InfluxDB aprovecha para lograr altas tasas de compresión, reduciendo significativamente los requisitos de almacenamiento en comparación con bases de datos de propósito general.

- Realizar consultas rápidas y agregaciones sobre rangos de tiempo específicos, esenciales para *dashboards* de monitoreo y reportes de uso.

- Manejar el flujo constante de eventos y métricas generadas por miles de usuarios y componentes del sistema.

#figure(
  image("../../src/images/Series de Tiempo.png"),
  caption: "Diagrama Conceptual de InfluxDB para Series de Tiempo. Nota. Este diagrama ilustra cómo InfluxDB se utiliza para recolectar y analizar datos con una fuerte componente temporal, como métricas de rendimiento del sistema y actividad detallada del usuario. Elaboración propia."
)

En el contexto del LMS, InfluxDB permitirá no solo operar, sino también monitorear y optimizar su propio rendimiento y la experiencia del usuario a un nivel de detalle inalcanzable con bases de datos relacionales para este tipo de carga de trabajo:

- *Métricas de Rendimiento de la Plataforma (platform_performance)*: Seguimiento del uso de CPU, memoria, latencia de red y tiempos de respuesta de los servicios en tiempo real.

- *Actividad Detallada del Usuario (user_activity)*: Recopilación granular de interacciones (*clics*, tiempo en página, progreso en videos, *scroll*) para análisis de comportamiento y personalización.

- *Compromiso con el Curso (course_engagement)*: Métricas sobre cómo los estudiantes interactúan con los materiales didácticos y las evaluaciones.

- *Alertas del Sistema (system_alerts)*: Registro de eventos importantes del sistema con su marca de tiempo exacta para análisis forense y resolución de problemas.

== Estrategia de Acceso a Datos
Como componente crítico de la arquitectura del LMS, se define cómo las diferentes capas del sistema interactuarán con las bases de datos subyacentes. Dada la adopción de una persistencia políglota, es imperativo establecer un enfoque cohesivo que gestione la complejidad de múltiples tecnologías de bases de datos, garantizando al mismo tiempo rendimiento, mantenibilidad y escalabilidad.

El objetivo principal es proporcionar una abstracción clara y consistente para los servicios de negocio, de modo que no necesiten conocer los detalles de implementación de cada base de datos. Esto facilita el desarrollo, las pruebas y el mantenimiento, y permite futuras migraciones o cambios en las tecnologías de persistencia con un impacto mínimo en la lógica de negocio.

=== Capa de Abstracción de Datos (Data Access Layer - DAL)
Se implementará una capa de acceso a datos dedicada que servirá como interfaz principal entre los servicios de negocio del LMS y las diferentes bases de datos. Esta capa será responsable de:

1. *Encapsular la lógica de persistencia*: Todos los detalles específicos de cada base de datos (queries SQL, operaciones de MongoDB, comandos de Redis, etc.) se gestionarán dentro de esta capa.

2. *Mapeo de Objetos*: Convertir los datos recuperados de las bases de datos a objetos de dominio (POJOs/DTOs) que los servicios de negocio puedan utilizar fácilmente, y viceversa.

3. *Manejo de Transacciones*: Gestionar la lógica transaccional, especialmente para operaciones que involucren múltiples operaciones o bases de datos (aunque la consistencia final sería el objetivo para operaciones políglotas).

4. *Gestión de Conexiones*: Manejar la creación, el mantenimiento y el cierre de conexiones a las bases de datos de manera eficiente.

5. *Manejo de Errores de Persistencia*: Capturar y normalizar las excepciones relacionadas con la base de datos para que puedan ser manejadas de manera uniforme por capas superiores.

=== Uso de ORM (Object-Relational Mapping) 
Para la base de datos principal PostgreSQL (reactiva), se utilizará un ORM (como Spring Data R2DBC en el ecosistema Java y TypeORM en TypeScript/Node.js) para simplificar la interacción con los datos.

1. *Productividad*: Reduce la cantidad de código boilerplate (código repetitivo) necesario para interactuar con la base de datos, permitiendo a los desarrolladores centrarse en la lógica de negocio. Esto permitirá

2. *Orientación a Objetos*: Permite a los desarrolladores trabajar con objetos Java/TypeScript en lugar de filas y columnas, lo que se alinea con el diseño del diagrama de clases.

3. *Seguridad*: Ayuda a prevenir ataques de inyección SQL al parametrizar automáticamente las consultas.

4. *Portabilidad*: Facilita el cambio de un motor de base de datos relacional a otro (aunque en este caso PostgreSQL es la elección principal).

5. *Reactividad*: Se utilizarán drivers y frameworks que soporten la programación reactiva (ej. R2DBC) para optimizar el uso de recursos y mejorar la escalabilidad bajo cargas concurrentes.

=== Drivers Nativos y Cliente Específicos
Para MongoDB e InfluxDB, se utilizarán los drivers oficiales y bibliotecas cliente específicos proporcionados por cada tecnología. Para Redis, se usará un cliente robusto (ej. Jedis o Lettuce en Java). Esto permitirá:

1. *Rendimiento Óptimo*: Los drivers nativos están optimizados para el protocolo y el modelo de datos específico de cada base de datos, lo que garantiza el mejor rendimiento posible.

2. *Acceso a Funcionalidades Específicas*: Permiten el acceso directo a las características únicas de cada base de datos (ej., consultas de agregación complejas en MongoDB, consultas de series de tiempo en InfluxDB, comandos Pub/Sub en Redis).

3. *Flexibilidad*: Ofrecen un mayor control sobre las operaciones a bajo nivel, lo que es crucial para escenarios donde el rendimiento o la funcionalidad específica es primordial.

=== Gestión de Conexiones y Connection Pooling
Para todas las bases de datos, se implementará una pool de conexiones (ej., HikariCP para R2DBC, o pools específicos para MongoDB, Redis, InfluxDB). Esto permitirá: 

1. Reutilizar las conexiones a la base de datos en lugar de abrirlas y cerrarlas para cada solicitud, reduciendo la sobrecarga y mejorando el rendimiento.

2. Mantener un número óptimo de conexiones listas para su uso.

3. Gestionar la disponibilidad y el estado de las conexiones.

=== Estrategias de Caché Explícitas
Se implementarán estrategias de caché explícitas utilizando Redis en la capa de acceso a datos para los datos más consultados. Esto permitirá: 

1. *Read-Through/Write-Through*: Para los datos que se leen y escriben con frecuencia, la DAL puede implementar lógica para consultar el caché antes de ir a la base de datos principal, y escribir en el caché y la base de datos simultáneamente.

2. *Lazy Loading/Write-Back*: Para otros escenarios, el caché puede cargarse bajo demanda o los cambios pueden escribirse en el caché primero y luego sincronizarse con la base de datos.

3. *Invalidación de Caché*: Se definirán políticas claras para la invalidación del caché cuando los datos subyacentes cambien, para asegurar la consistencia.

=== Consideraciones de Consistencia
Dada la arquitectura políglota, la consistencia entre bases de datos será manejada a nivel de aplicación (eventual consistency) para ciertas operaciones, o se diseñarán procesos específicos para la sincronización cuando sea necesaria una consistencia más fuerte.

- *PostgreSQL*: Se mantendrá una consistencia transaccional ACID para los datos relacionales críticos.

- *MongoDB, InfluxDB, Redis*: Se operará con una consistencia eventual, donde los datos pueden tardar un breve tiempo en propagarse, lo cual es aceptable para logs, métricas y datos de caché.

#pagebreak()