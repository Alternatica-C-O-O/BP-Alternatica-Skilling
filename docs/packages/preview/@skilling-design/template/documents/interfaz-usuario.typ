= Interfaz de Usuario (UI/UX)
Para el éxito del Sistema de Gestión de Aprendizaje (LMS), una UI (Interfaz de Usuario) bien diseñada no solo es estéticamente agradable, sino que también es intuitiva, accesible y eficiente, permitiendo a estudiantes, docentes y administradores interactuar con el sistema de manera fluida y sin frustraciones. La UX (Experiencia de Usuario), por su parte, abarca la totalidad de la experiencia del usuario al interactuar con el LMS, desde la facilidad de navegación y la claridad de la información hasta la satisfacción general con el uso de la plataforma. El diseño centrado en el usuario permitirá asegurar que las necesidades y flujos de trabajo de los distintos perfiles de usuario estén en el centro de cada decisión de diseño.

Nuestro enfoque en la UI/UX se alineará directamente con los Requisitos No Funcionales (RNF) previamente definidos, especialmente aquellos relacionados con la Usabilidad (RNF3) y la Portabilidad (RNF8). Esto significa que la interfaz deberá ser intuitiva, consistente y fácil de navegar, garantizando la accesibilidad para usuarios con diversas habilidades tecnológicas (RNF3.1 y RNF3.2). Además, se priorizará la compatibilidad con múltiples navegadores y la responsividad para dispositivos móviles (RNF8.1 y RNF8.2), asegurando una experiencia uniforme y de alta calidad sin importar el dispositivo o el entorno de acceso del usuario. Se prestará especial atención a la claridad del diseño (RNF3.3) y a la retroalimentación visual, elementos esenciales para guiar al usuario y resolver problemas de manera efectiva.

== Flujo de Usuario y Mapas de Navegación
Comprender cómo los usuarios interactuarán con el LMS es fundamental para diseñar una experiencia de usuario eficaz y sin fricciones. Los flujos de usuario detallan el camino que un usuario sigue para completar una tarea específica dentro del sistema, mientras que los mapas de navegación (o mapas de sitio) ofrecen una visión holística de la estructura de la información y la jerarquía de las páginas. Juntos, estas herramientas visualizan la arquitectura de la información del LMS, asegurando que los usuarios puedan encontrar lo que necesitan de manera intuitiva y que la navegación sea lógica y consistente a través de los diferentes roles y funcionalidades.

#figure(
  image("../../src/images/Mapa de Sitio-Skilling LMS.drawio.png"),
  caption: "Mapa de Sitio: Skilling LMS. Nota. Este diagrama representa visualmente la estructura organizacional de la plataforma, las principales secciones y subsecciones a las que los usuarios pueden acceder. Elaboración propia."
)

El Mapa de Sitio presentado es un diagrama representativo visual de la estructura organizacional de la plataforma, delineando las principales secciones y subsecciones a las que los usuarios pueden acceder. DE esta manera, podemos determinar la guía para la navegación, mostrando la jerarquía de las páginas y cómo se interconectan. De esta mnaera podemos obvar a través de esta la lógica de cómo se agrupa el contenido, lo cual es vital para el diseño de menús de navegación y barras laterales que puedan  garnatizar que los usuarios puedan orientarse fácilmente dentro del sistema y comprender su ubicación actual en la estructura general del LMS.

== Wireframes de Alto Nivel
Los wireframes son esquemas visuales de la interfaz de usuario que definen la estructura básica, el contenido principal y la disposición de los elementos en cada pantalla del LMS. Representan la primera etapa del diseño visual, centrándose en la funcionalidad, la jerarquía de la información y la interacción, sin distracciones de color, tipografía o gráficos finales. Su propósito es establecer la arquitectura de la información y la usabilidad antes de invertir tiempo en el diseño visual detallado. Este enfoque garantiza que los flujos de usuario sean lógicos y que los elementos clave sean fácilmente accesibles, facilitando la validación temprana con los interesados y optimizando el proceso de desarrollo.

=== Frontend Page
La página de inicio de sesión es el primer punto de contacto del usuario con el LMS, diseñada para ser clara y directa, facilitando un acceso rápido y seguro. Incluye campos para nombre de usuario y contraseña, con opciones de "Recordarme" y enlaces para recuperar la contraseña o registrar una nueva cuenta (Ver Figura 25). El diseño minimalista asegura una distracción mínima, enfocándose puramente en la tarea de autenticación para una experiencia de usuario eficiente desde el inicio.

#figure(
  image("../../src/images/inicio-sesion.png"),
  caption: "Wireframe de Página de Inicio de Sesión. Nota. Este wireframe muestra el diseño esencial de la interfaz de usuario para el ingreso al LMS, con campos de credenciales y opciones de recuperación/registro. Elaboración propia."
)

Una vez autenticado, la página principal (Ver Figura 26) actúa como el centro de mando para cada usuario. Presenta un *dashboard* intuitivo que resume la información más relevante: cursos activos, anuncios recientes, próximas tareas y un resumen del progreso. La disposición de los elementos prioriza la visibilidad de la información crítica, con menús de navegación claros para acceder a otras secciones del LMS, como el catálogo de cursos, perfil de usuario y notificaciones, brindando una navegación fluida y una visión general de su estado académico.

#figure(
  image("../../src/images/pagina-principal.png"),
  caption: "Wireframe de Página Principal (Dashboard). Nota. Este wireframe muestra el diseño del *dashboard* central del LMS, que ofrece un resumen personalizado de cursos, anuncios y progreso para el usuario autenticado. Elaboración propia."
)

Continuando, la página de la institución (Ver Figura 27) ofrece una visión general de la universidad o entidad educativa, mostrando su misión, visión, historia y valores. Es un espacio informativo para estudiantes potenciales y existentes, con secciones dedicadas a noticias, eventos y enlaces útiles que brindan la identidad y los recursos disponibles para la comunidad académica.

#figure(
  image("../../src/images/pagina-institucion.png"),
  caption: "Wireframe de Página de la Institución. Nota. Este wireframe presenta el diseño de una página informativa dentro del LMS que detalla la misión, visión, historia y valores de la institución . Elaboración propia."
)

Por otra parte, la página de perfil de usuario (Ver Figura 28) es el centro de personalización del LMS, permitiendo a cada usuario gestionar su información personal, académica y de contacto. Aquí pueden actualizar sus datos, ver su historial de cursos, revisar sus calificaciones y configurar preferencias de notificación. Dado que el enfoque está orientado a la calidad del usuario, se debe buscar desarrollar un diseño claro y modular que facilite la edición y consulta rápida de la información, para que, de esta manera, lograr mantener su perfil actualizado y relevante.

#figure(
  image("../../src/images/pagina-perfil-usuario.png", width: 35em),
  caption: "Wireframe de Página de Perfil de Usuario. Nota. Este wireframe ilustra el diseño de la interfaz donde los usuarios pueden gestionar su información personal, académica e historial. Elaboración propia."
)

En la sección de cursos (Ver Figura 29) es un catálogo completo donde los usuarios pueden explorar la oferta académica del LMS. Presenta los cursos disponibles con detalles clave como descripción, créditos y requisitos previos. Lo que permite la búsqueda y filtrado eficiente, ayudando a los estudiantes a encontrar cursos relevantes y a los docentes a gestionar sus propias ofertas.

#figure(
  image("../../src/images/pagina-cursos.png"),
  caption: "Wireframe de Página de Cursos. Nota. Este wireframe ilustra el diseño de la interfaz que muestra el catálogo completo de cursos disponibles, con opciones de búsqueda y filtrado para facilitar la exploración de la oferta académica. Elaboración propia."
)

El calendario de actividades (Ver Figura 30) ofrece una vista de todas las fechas importantes: exámenes, entregas de trabajos, sesiones en vivo y eventos del curso. Permite a los usuarios organizar su tiempo eficazmente, con opciones de filtrado por curso o tipo de actividad. Siendo necesario en este aspecto brindar una mejor claridad visual y la facilidad para añadir recordatorios son cruciales para que estudiantes y docentes gestionen sus compromisos académicos de forma efectiva.

#figure(
  image("../../src/images/pagina-calendario-actividades.png"),
  caption: "Wireframe de Página de Calendario de Actividades. Nota. Este wireframe presenta el diseño de la interfaz del calendario que muestra fechas importantes como exámenes, entregas y eventos, con opciones de filtrado para una gestión eficiente del tiempo. Elaboración propia."
)

La interfaz de mensajería (Ver Figura 31) es un sistema de comunicación interna que facilita la interacción entre estudiantes, docentes y administradores. Lo que permite el envío y recepción de mensajes privados, así como la participación en foros de discusión y grupos de estudio. El diseño debe ser un tipo chat moderno que asegure una comunicación fluida y organizada, con funcionalidades como bandejas de entrada y salida, búsquedas de mensajes y adjuntos, mejorando la colaboración y el soporte dentro de la plataforma.

#figure(
  image("../../src/images/pagina-mensajes.png"),
  caption: "Wireframe de Página de Mensajería. Nota. Este wireframe ilustra el diseño de la interfaz de comunicación interna del LMS, facilitando mensajes privados y participación en foros para una interacción fluida entre usuarios. Elaboración propia."
)

En contraste, la sección de calificaciones (Ver Figura 32) ofrece una visión detallada del rendimiento académico de los estudiantes en cada curso. Permite a los docentes registrar y gestionar puntuaciones de exámenes y tareas, mientras que los estudiantes pueden revisar sus notas, el progreso general y la retroalimentación. Por lo que, las funcionalidades de filtro facilitan el seguimiento del desempeño, asegurando transparencia y un acceso rápido a la información académica para todos los usuarios.

#figure(
  image("../../src/images/pagina-calificaciones.png"),
  caption: "Wireframe de Página de Calificaciones. Nota. Este wireframe presenta el diseño de la interfaz que muestra el rendimiento académico, permitiendo a docentes gestionar y a estudiantes revisar sus calificaciones y progreso. Elaboración propia."
)

Las herramientas del curso (Ver Figura 33) proporcionan un conjunto de funcionalidades para docentes y estudiantes, optimizando la gestión y el aprendizaje. Incluyen recursos como foros de discusión, envío de tareas, creación de encuestas y herramientas de colaboración. Esta sección debe consolidar todas las utilidades necesarias para interactuar con el contenido del curso y entre pares, promover un ambiente de aprendizaje interactivo y eficiente.

#figure(
  image("../../src/images/herramientas-curso.png"),
  caption: "Wireframe de Página de Herramientas del Curso. Nota. Este wireframe ilustra el diseño de la interfaz que consolida diversas funcionalidades para docentes y estudiantes, como foros, tareas y herramientas colaborativas dentro de un curso. Elaboración propia."
)

En suma, podemos mencionar que los actores, tanto administradores como docentes, cumplen el rol de organizar, subir y actualizar los materiales didácticos de manera eficiente. Proporcionan herramientas para categorizar recursos, establecer permisos de acceso y asegurar la integridad de los archivos. De esta manera, una plataforma con un diseño intuitivo facilita la administración de grandes volúmenes de información, garantizando que el contenido educativo esté siempre actualizado y sea accesible para los usuarios.

=== Documentación 
La documentación de la API es un componente crucial para el desarrollo y la integración con el LMS. Utilizando Docusaurus, se construirá una plataforma de documentación que ofrezca una visión clara y completa de todos los servicios y sus *endpoints*. Este enfoque facilitará a los desarrolladores internos y externos entender cómo interactuar con el sistema, consumir sus funcionalidades y construir extensiones. La documentación detalla las solicitudes (*request*), respuestas (*response*), parámetros, modelos de datos y métodos de autenticación para cada operación, garantizando una experiencia fluida para cualquier equipo que necesite integrarse con el LMS.

#figure(
  image("../../src/images/Documentation_Page.png"),
  caption: "Wireframe de la Página Principal de Documentación de la API.Nota. Este wireframe muestra el diseño de la interfaz de la documentación de la API, diseñada para proporcionar una visión general de los servicios del LMS y facilitar la búsqueda de información relevante para desarrolladores. Elaboración propia."
)

La página principal de documentación será la primera parte en la cual se podrá interactuar directamente con todos los servicios disponibles en el LMS. Permite a los desarrolladores buscar rápidamente APIs específicas, explorar la arquitectura de microservicios y comprender la interconexión entre ellos. Se busca una estructura clara y funcional de búsqueda integrada para poder asegurar que la información crítica esté siempre al alcance, acelerando el proceso de desarrollo e integración de cualquier módulo o característica nueva en la plataforma.

#figure(
  image("../../src/images/financial-service-doc.png"),
  caption: "Wireframe de la Documentación del Servicio Financiero. Nota. Este wireframe ilustra el diseño de la documentación de la API para la gestión financiera, detallando los *endpoints* relacionados con pagos, matrículas y transacciones económicas dentro del LMS. Elaboración propia."
)

Por otra parte, la documentación del Servicio Financiero debe lograr detallar todas las APIs relacionadas con la gestión de pagos, matrículas, planes de precios y transacciones económicas dentro del LMS. Aquí los desarrolladores encontrarán *endpoints* para procesar inscripciones pagadas, gestionar facturaciones, aplicar descuentos y auditar movimientos financieros. Cada API especifica los modelos de datos para transacciones, métodos de pago y estados de procesamiento, esencial para integraciones con pasarelas de pago externas o sistemas de contabilidad.

#figure(
  image("../../src/images/content-service-doc.png"),
  caption: "Wireframe de la Documentación del Servicio de Contenido. Nota. Este wireframe presenta el diseño de la documentación de la API para la manipulación programática de todos los materiales educativos del LMS, incluyendo la gestión de cursos, módulos, lecciones y recursos didácticos. Elaboración propia."
)

El Servicio de Contenido se documenta para permitir la manipulación programática de todos los materiales educativos del LMS. Incluye APIs para crear, actualizar, eliminar y recuperar cursos, módulos, lecciones y recursos didácticos (videos, documentos, *quizzes*). Se detallan las estructuras JSON para la organización del contenido, versiones y metadatos asociados, siendo esta sección una herramienta para la autoría, migraciones de contenido y extensiones que necesiten interactuar directamente con la biblioteca de aprendizaje.

#figure(
  image("../../src/images/users-service-doc.png"),
  caption: "Wireframe de la Documentación del Servicio de Usuarios. Nota. Este wireframe ilustra el diseño de la documentación de la API para la gestión de perfiles, roles y permisos de los usuarios en el LMS, cubriendo operaciones de autenticación y administración. Elaboración propia."
)

La documentación del Servicio de Usuarios proporciona APIs detalladas para gestionar perfiles, roles y permisos de todos los tipos de usuarios en el LMS. Cubre operaciones como autenticación, registro, recuperación de credenciales y administración de la información personal de cada usuario, siendo una sección fundamental para cualquier integración que requiera interactuar con la base de usuarios de la plataforma.

#figure(
  image("../../src/images/resource-planning-service.png"),
  caption: "Wireframe de la Documentación del Servicio de Planificación de Recursos. Nota. Este wireframe presenta el diseño de la documentación de la API para la asignación y gestión de recursos físicos y humanos, incluyendo la programación de aulas y la asignación de docentes a cursos. Elaboración propia."
)

El Servicio de Planificación de Recursos documenta las APIs para la asignación y gestión de recursos físicos y humanos dentro del LMS. Esto incluye la programación de aulas, laboratorios, equipos y la asignación de docentes a cursos.

#figure(
  image("../../src/images/assessments-service.png"),
  caption: "Wireframe de la Documentación del Servicio de Evaluaciones. Nota. Este wireframe ilustra el diseño de la documentación de la API para la creación, administración y calificación de exámenes, tareas y proyectos dentro del LMS. Elaboración propia."
)

La documentación del Servicio de Evaluaciones detalla las APIs para crear, administrar y calificar exámenes, tareas y proyectos. Permite a los docentes definir rúbricas, asignar puntuaciones y proporcionar retroalimentación a los estudiantes. Además, facilita la integración con herramientas de terceros para la evaluación y el análisis de resultados académicos.

#figure(
  image("../../src/images/curriculum-service.png"),
  caption: "Wireframe de la Documentación del Servicio de Currículo. Nota. Este wireframe presenta el diseño de la documentación de la API para gestionar la estructura académica del LMS, incluyendo planes de estudio, programas y competencias. Elaboración propia."
)

El Servicio de Currículo expone APIs que permiten gestionar la estructura académica del LMS, incluyendo planes de estudio, programas de cursos, modelos educativos y competencias. Facilita la creación y actualización de la oferta educativa, asegurando que el contenido curricular esté siempre alineado con los objetivos pedagógicos y los requisitos institucionales.

#figure(
  image("../../src/images/notifications-service.png"),
  caption: "Wireframe de la Documentación del Servicio de Notificaciones.Nota. Este wireframe ilustra el diseño de la documentación de la API para el envío y la gestión de comunicaciones, como alertas y mensajes, dentro del LMS. Elaboración propia."
)

La documentación del Servicio de Notificaciones proporciona las APIs para el envío y la gestión de comunicaciones dentro del LMS. Permite a los servicios y a los usuarios generar alertas, mensajes internos, notificaciones por correo electrónico o SMS. Esta sección es esencial para mantener a estudiantes, docentes y administradores informados sobre eventos importantes, actualizaciones y actividades relevantes.

#figure(
  image("../../src/images/reporting-service.png"),
  caption: "Wireframe de la Documentación del Servicio de Reportes. Nota. Este wireframe presenta el diseño de la documentación de la API para la generación programática de informes y análisis de datos del LMS, incluyendo rendimiento académico y participación de usuarios. Elaboración propia."
)

El Servicio de Reportes documenta las APIs para la generación programática de informes y análisis de datos del LMS. Permite extraer información sobre el rendimiento académico, la participación de los usuarios, el uso de recursos y otras métricas clave, lo cual será de ayuda para la toma de decisiones basada en datos y para el cumplimiento de requisitos de auditoría y seguimiento.

#figure(
  image("../../src/images/enrollment-service.png"),
  caption: "Wireframe de la Documentación del Servicio de Inscripciones.Nota. Este wireframe ilustra el diseño de la documentación de la API para la matriculación y desmatriculación de estudiantes en cursos, cubriendo el proceso completo de gestión de inscripciones. Elaboración propia."
)

La documentación del Servicio de Inscripciones detalla las APIs para la matriculación y desmatriculación de estudiantes en cursos, programas o actividades. Cubre el proceso completo de gestión de inscripciones, incluyendo el seguimiento del estado, la validación de requisitos previos y la asignación de cupos. Ayudará a automatizar el proceso de matriculación y gestionar la población estudiantil.

#pagebreak()