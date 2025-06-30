= Lógica de Negocio y Casos de Uso

En concepto, este Sistema de Gestión del Aprendizaje (LMS) surge de una identificación precisa de las ineficiencias operacionales inherentes a las plataformas educativas existentes, cuyo *backend* se caracteriza por su naturaleza estática, manual y reactiva. Se ha documentado una gestión administrativa fundamentada en procesos ineficaces, como la dependencia de hojas de cálculo para la administración de usuarios y docentes, una proliferación de *tickets* para cambios menores y una marcada disociación entre el análisis de datos y la ejecución de acciones proactivas. Esta deficiencia, manifestada como una falta de dinamismo intrínseco, impulsó la necesidad de transcender las funcionalidades básicas del LMS hacia una solución con capacidad de automatización e inteligencia prescriptiva. En consecuencia, se formuló el concepto de un Intelligent Core System (ICS), diseñado para transformar la gestión del aprendizaje de un modelo reactivo a uno dinámico, proactivo y centrado en la personalización del usuario.

El objetivo principal de este proyecto se define como el diseño e implementación de un LMS de software libre, con la finalidad de democratizar el acceso educativo y fortalecer las competencias digitales, optimizando la dinámica del proceso de enseñanza-aprendizaje en el ámbito superior y especializado.

Para alcanzar este propósito, se ha completado un diagnóstico exhaustivo que ha permitido la definición precisa de identidades únicas y sus relaciones, estableciendo la estructura fundamental para la información del sistema conforme a los procesos estandarizados del AEP (Observar y revisar el Manual de Investigación y Propuesta de Proyecto). De esta manera, tras haber identificado el desarrollo de los principales procesos y operaciones realizadas por un organismo, se busca identificar a través de procesos secuenciales y de análisis la redefinición y conceptualización de los requerimientos a requisitos funcionales y no funcionales medibles.

== Modelado de Casos de Uso
El uso del presente recurso es fundamental para abstraer y documentar las funcionalidades del LMS desde la perspectiva de sus actores externos, delineando las interacciones que el sistema debe facilitar. Este enfoque, en contraste con los modelos de datos, permite una comprensión directa de "qué" puede hacer el sistema, sin adentrarse en detalles de implementación. La identificación de actores en la fase de análisis —Administrador, Docente, Estudiante y Sistemas Externos— ha sido de gran beneficio para comprender cómo estos actúan de forma general en el desarrollo y uso de un sistema LMS, permitiendo mapear las responsabilidades y los puntos de interacción de cada rol con el LMS. Al definir las funcionalidades a través de casos de uso específicos, como "Gestionar Usuarios", "Inscribirse en Cursos" o "Crear Evaluaciones", se establece un lenguaje común que unifica la comprensión entre los equipos de desarrollo, los *stakeholders* y los futuros usuarios, asegurando que el diseño del sistema responda directamente a las necesidades operacionales y pedagógicas previamente diagnosticadas; por lo que, la granularidad en el modelado es esencial para una planificación precisa de las fases de desarrollo y para la priorización de funcionalidades.

De esta manera, podemos definir que las organizaciones educativas, que representan la entidad de modelado de estos casos de uso, se estructuran en paquetes lógicos (e.g., Gestión de Usuarios y Roles, Oferta Académica, Programación y Recursos, Gestión de Contenido, Interacción Estudiante-Curso, Evaluaciones y Calificaciones, Reportes y Auditoría, Gestión de Notificaciones y Gestión Financiera). Esto hace referencia, de forma análoga, a las etapas que conlleva la continuidad de negocio y la definición de las reglas inherentes para el funcionamiento de la organización y el uso de un LMS integral.

Por lo que, se quiere hacer referencia a que cada paquete encapsula un conjunto de funcionalidades coherentes, lo que facilitará la asignación de tareas a los equipos de desarrollo y la gestión de dependencias entre módulos. De esta manera, definimos los actores que presentan la mayor carga de flujo de procesos, siendo estos los actores de *"Docente"* y *"Estudiante"*, asistidos siempre por un *"Administrador"* y el uso externo de sistemas para la comunicación y desarrollo de clases virtuales que llevarán a cabo como parte de la gestión de contenidos y capacidad de servicios de TI (Visualizar Figura 1).

#figure(
  image("../../src/images/Diagrama Generla UML.png", width: 17.5em),
  caption: [Diagrama General de Casos de Uso del LMS. Nota. Este diagrama UML presenta una visión general de los casos de uso del Sistema de Gestión del Aprendizaje (LMS), mostrando las principales funcionalidades y las interacciones de los actores clave (Administrador, Docente, Estudiante y Sistemas Externos) con el sistema. Elaboración propia.]
)

Desarrolland más cada parte del esquemna general del caso de uso, podemos mencionar como parte principal del sistema y centro de este, la Gestión de Usuarios y Roles, la cual sienta las bases para el acceso controlado y la personalización de la experiencia en el LMS (Ver Figura 2). 

#figure(
  image("../../src/images/Usuarios y Roles.png", width: 45em),
   caption: [Diagrama de Casos de Uso: Gestión de Usuarios y Roles. Nota. Este diagrama ilustra las funcionalidades clave y las interacciones de los actores relacionadas con la administración de usuarios y la asignación de roles y permisos dentro del sistema LMS. Elaboración propia.]
)

Como se puede visualizar, este subsistema encapsula las funcionalidades esenciales que permiten la creación, modificación y administración de las identidades digitales dentro de la plataforma, desde estudiantes y docentes hasta administradores. Es aquí donde se define quién puede acceder al sistema, cómo se autentica y qué acciones específicas tiene permitido realizar.

De esta manera, en un sentido más específico, se debe gestionar tanto la asignación de roles y permisos como el poder asegurar que cada actor interactúe con las funcionalidades pertinentes a sus responsabilidades, optimizando la seguridad y la eficiencia operativa.

De forma subsecuente, la interacción directa tras el registro del usuario, el cual puede ser tanto un docente como un estudiante, se sigue con el acceso al apartado académico, el cual representa el núcleo educativo del LMS. Este subsistema de Oferta Académica está meticulosamente diseñado para estructurar, administrar y presentar el contenido curricular de la institución (Ver Figura 3). Aquí es donde se definen y gestionan los modelos educativos, los perfiles curriculares y las competencias que los estudiantes deben adquirir; lo que de forma directa permite la creación y organización de planes de estudio, y la configuración detallada de cada curso ofertado, incluyendo sus módulos, créditos y prerrequisitos. Para los docentes, este módulo proporciona las herramientas para organizar el material didáctico dentro de los módulos de curso y, por otra parte, para los estudiantes es el apartado que puede brindar apoyo al estudiante para poder obtener información y poder informarse de su progreso.

#figure(
  image("../../src/images/Oferta Académica.png", width: 40em),
  caption: "Diagrama de Casos de Uso: Oferta Académica. Nota. Este diagrama ilustra las funcionalidades principales relacionadas con la gestión y presentación del contenido curricular dentro del LMS, incluyendo la administración de planes de estudio y cursos para docentes y estudiantes. Elaboración propia."
)

Continuando con el flujo de uso del sistema, después de acceder a la oferta académica, el siguiente paso es la sección de Programación y Recursos (Ver Figura 4). Este subsistema está diseñado para la gestión eficiente de los horarios académicos, la asignación de recursos y la planificación de las actividades educativas. Permite a los administradores y docentes organizar las sesiones de clase, reservar aulas y laboratorios, y gestionar el material didáctico necesario para cada curso.

Si bien esta labor puede estar más relacionada con el rol de administrador, muchas veces se debe gestionar de forma externa, a través del uso de otros módulos o sistemas, para una mayor facilidad de petición por parte del docente. Esto, a su vez, facilita a los estudiantes la consulta de sus horarios y la disponibilidad de los recursos. De esta manera, se puede identificar que debe existir una comunicación bidireccional y directa entre el docente y el administrador de recursos.

#figure(
  image("../../src/images/Gestión de Contenido.png"),
  caption: "Diagrama de Casos de Uso: Programación y Recursos. Nota. Este diagrama ilustra las funcionalidades relacionadas con la gestión de horarios, asignación de aulas, reserva de equipos y la disponibilidad de materiales didácticos para los cursos y actividades académicas. Elaboración propia."
)

En referencia a la programación de recursos, el envío automatizado y manual de alertas, recordatorios y mensajes personalizados asegura que tanto estudiantes como docentes reciban información oportuna sobre calificaciones, nuevas asignaciones, fechas límite o anuncios importantes. Esto permite configurar preferencias individuales relevantes y reduce la saturación de información, optimizando el flujo de interacción y compromiso (Ver Figura 5).

#figure(
  image("../../src/images/Gestión de Notificaciones.png"),
  caption: "Diagrama de Casos de Uso: Gestión de Notificaciones. Nota. Este diagrama ilustra cómo el sistema gestiona el envío de alertas, recordatorios y mensajes personalizados para mantener informados a estudiantes y docentes sobre eventos y actualizaciones relevantes. Elaboración propia."
)

La interacción entre el estudiante y el curso representa el epicentro de la experiencia de aprendizaje dentro del LMS, concentrando las funcionalidades que facilitan la participación activa y el seguimiento individualizado del progreso académico. Este subsistema ayuda a los estudiantes a inscribirse en los cursos de su interés, registrar su asistencia a las sesiones y monitorear de forma detallada su avance a través de cada módulo (Ver Figura 6).

Además, es el conducto principal para la comunicación bidireccional, permitiendo a estudiantes y docentes enviar y recibir mensajes, así como participar activamente en foros de discusión, creando y respondiendo publicaciones; fomentando así un entorno colaborativo y de apoyo, además de poder garantizar que los usuarios puedan interactuar con el contenido y con la comunidad educativa.

#figure(
  image("../../src/images/Estudiante-Curso.png", width: 33em),
  caption: "Diagrama de Casos de Uso: Interacción Estudiante-Curso. Nota. Este diagrama ilustra las funcionalidades que permiten a los estudiantes inscribirse, seguir su progreso, comunicarse con docentes y participar en foros dentro de los cursos del LMS. Elaboración propia."
)

En consecuencia, la interacción entre estudiantes y recursos (cursos) conlleva a que el estudiante, a través de una evaluación, pueda medir su progreso y rendimiento académico. Por ello, se debe tener un subsistema que pueda ofrecer a los docentes las herramientas necesarias para crear y gestionar diversas formas de evaluación, desde exámenes y tareas hasta proyectos y cuestionarios, asignando su peso relativo en la calificación final.

Una vez completadas las evaluaciones, el módulo permite registrar las calificaciones obtenidas por los estudiantes, así como proporcionar comentarios detallados. Los estudiantes, por su parte, tienen la capacidad de consultar sus calificaciones de forma transparente, mientras que el sistema también puede emitir certificados una vez que se han cumplido los requisitos académicos (Ver Figura 7).

#figure(
  image("../../src/images/Evaluaciones y Calificaciones.png",width: 45em),
  caption: "Diagrama de Casos de Uso: Evaluaciones y Calificaciones. Nota. Este diagrama ilustra las funcionalidades para que los docentes creen, gestionen y califiquen evaluaciones, y para que los estudiantes consulten sus resultados y obtengan certificados. Elaboración propia."
)

Con relación a los aspectos económicos, el LMS debe estar vinculado a la oferta académica, permitiendo a la institución administrar los planes de precios y las transacciones de pago. Por lo tanto, es necesario que, a través de un subsistema, se habilite el registro y seguimiento de todas las transacciones de pago realizadas por los estudiantes, incluyendo la interacción con sistemas externos de procesamiento para garantizar una conciliación precisa y segura. Adicionalmente, debe proporcionar a los estudiantes la capacidad de consultar su historial de pagos, lo que promueve la transparencia y el control financiero personal. Siendo así un componente para el sostenimiento operativo del LMS y aseguramiento de que los procesos de cobro sean fluidos, transparentes y totalmente integrados (Ver Figura 8).

La funcionalidad de este módulo se extiende más allá de la mera transacción, ya que permite un control administrativo sobre los ingresos generados por la plataforma. Esto incluye la supervisión de los estados de pago, la gestión de posibles reembolsos y la generación de reportes financieros que son esenciales para la toma de decisiones estratégicas. La integración con sistemas externos de pago minimiza la intervención manual y optimiza la eficiencia operativa, reduciendo la probabilidad de errores. Así, este módulo facilita el acceso a la oferta educativa para los estudiantes, como también dota a la institución de las herramientas necesarias para una administración económica coherente y auditable, asegurando la sostenibilidad y el crecimiento a largo plazo del ecosistema de aprendizaje.

#figure(
  image("../../src/images/Gestión Financiera.png"),
  caption: "Diagrama de Casos de Uso: Gestión Financiera. Nota. Este diagrama ilustra las funcionalidades relacionadas con la administración de planes de precios, transacciones de pago, seguimiento de historial y la integración con sistemas de procesamiento externos dentro del LMS. Elaboración propia."
)

Dado lo mencionado por los requerimientos, se entiende la necesidad de trazabilidad de información, lo cual pueda ofrecer visibilidad sobre el rendimiento del sistema, el progreso académico y las interacciones de los usuarios. De esta manera, los docentes generan reportes personalizados que pueden abarcar desde estadísticas de inscripción y rendimiento estudiantil hasta el uso de recursos y la actividad de la plataforma. Fundamentalmente, también gestiona el registro detallado de la actividad del usuario (*logs*), lo cual tiene el propósito de auditoría, seguridad y análisis de comportamiento. De esta manera, se favorece el rastreo auditable de las operaciones para la mejora continua, el cumplimiento normativo y la validación de la efectividad.

#figure(
  image("../../src/images/Reportes y Auditoría.png"),
  caption: "Diagrama de Casos de Uso: Reportes y Auditoría. Nota. Este diagrama ilustra las funcionalidades del LMS relacionadas con la generación de reportes sobre el rendimiento del sistema y progreso. Elaboración Propia."
)

== Modelado del Comportamiento del Sistema
Tras haber establecido la estructura estática del LMS mediante los diagramas de funcionalidades a través de los casos de uso, el siguiente paso es comprender y documentar la dinámica interna del sistema. El modelado del comportamiento nos permite visualizar cómo interactúan los componentes del software y los actores a lo largo del tiempo, y cómo se ejecutan los procesos de negocio. Esto se logra mediante diagramas que ilustran el flujo de control, las decisiones y las secuencias de mensajes. Al detallar estos comportamientos, aseguramos una implementación precisa y una depuración más eficiente, lo que es vital para la estabilidad y funcionalidad del LMS en un entorno real.

#figure(
  image("../../src/images/Registro y Onboarding de Usuario.png"),
  caption: "Diagrama de Actividad: Registro y Onboarding de Usuario. Nota. Este diagrama ilustra el flujo de acciones para el registro y la activación de nuevas cuentas de usuario en el LMS, detallando los pasos que realizan los usuarios o administradores y las validaciones del sistema para una incorporación segura y eficiente. Elaboración propia."
)

El proceso de Registro y Onboarding de Usuario es el primer punto de contacto del usuario con el LMS y un flujo para asegurar una incorporación fluida. Este diagrama de actividad detalla las acciones que un usuario o un administrador realizan para crear una nueva cuenta y los pasos que el sistema ejecuta para validarla y activarla. Comienza con la captura de datos y puede implicar una verificación por correo electrónico o una creación directa por parte de un administrador. Posteriormente, el sistema asigna un rol inicial y, si es necesario, puede requerir una aprobación manual. La culminación de este flujo se da cuando la cuenta es activada y el usuario recibe sus credenciales, lo que le permite acceder plenamente a las funcionalidades del LMS; lo cual ayuda a establecer la base de usuarios y garantizar la seguridad del acceso.

Subsecuentemente, podemos hablar de la forma en que los estudiantes se incorporan a la oferta académica del LMS a través del proceso de Inscripción en un Curso (Ver Figura 11). Este diagrama de actividad detalla el flujo que un estudiante sigue desde que selecciona un curso hasta que obtiene acceso a sus contenidos. El sistema gestiona automáticamente las validaciones, como la verificación de prerrequisitos y la gestión de los pagos asociados si el curso tiene un costo. Si todas las condiciones se cumplen, la inscripción se registra y el estudiante recibe la confirmación, lo que le permite comenzar su aprendizaje de inmediato. 

#figure(
  image("../../src/images/Inscripción en un Curso.png"),
  caption: "Diagrama de Actividad: Inscripción en un Curso. Nota. Este diagrama ilustra el flujo de acciones y validaciones que un estudiante realiza para inscribirse en un curso dentro del LMS, desde la selección hasta la confirmación de acceso al contenido. Elaboración propia."
)

Por otra parte, el Proceso de Calificación y Retroalimentación es un componente fundamental para la evaluación del aprendizaje y el desarrollo continuo del estudiante. Como mencionamos anteriormente, lo que se quiere representar en este diagrama de actividad (Ver Figura 12) es la ilustración del flujo que sigue un docente desde la revisión de una evaluación entregada hasta el registro de la calificación y la provisión de comentarios constructivos.

#figure(
  image("../../src/images/Proceso de Calificación y Retroalimentación.png", width: 25em),
  caption: "Diagrama de Actividad: Proceso de Calificación y Retroalimentación. Nota. Este diagrama ilustra el flujo de trabajo del docente para evaluar, asignar calificaciones y proporcionar comentarios. Elaboración propia."
)

La Generación y Gestión de Solicitudes permite centralizar y optimizar la comunicación de requerimientos específicos de los usuarios al personal administrativo o de soporte. Este diagrama de actividad detalla el flujo que sigue un usuario, ya sea estudiante o docente, para iniciar una solicitud, seleccionando su tipo y detallando su necesidad. El sistema registra la solicitud y notifica a los administradores pertinentes para su revisión. Si se requiere información adicional, el sistema facilita esta interacción bidireccional. Finalmente, un administrador toma una decisión, el sistema actualiza el estado y notifica al usuario sobre la resolución, garantizando transparencia y eficiencia en la atención a las necesidades (Ver Figura 13).

#figure(
  image("../../src/images/Generación y Gestión de Solicitudes.png", width: 29.9em),
  caption: "Diagrama de Actividad: Generación y Gestión de Solicitudes. Nota. Este diagrama ilustra el flujo completo para que los usuarios envíen solicitudes y el personal administrativo las gestione. Elaboración propia."
)

Se puede especificar que este permite mantener una operación fluida y una alta satisfacción del usuario dentro del LMS, ya que proporciona un canal para la resolución de problemas y la gestión de requerimientos que no se cubren con las funcionalidades automáticas. Al formalizar esta interacción, se reduce la ambigüedad y se mejora el tiempo de respuesta, lo cual ayuda a tener una experiencia de usuario positiva. La capacidad de rastrear cada solicitud desde su origen hasta su resolución final también facilita la auditoría y permite identificar patrones de problemas comunes, contribuyendo a la mejora continua del sistema y de los servicios ofrecidos.

=== Definición de Secuencia de Actividades
Una vez que los flujos de trabajo generales del LMS han sido delineados mediante los diagramas de actividad, el siguiente paso es el modelado del comportamiento para la definición de la secuencia de actividades. Esto implica detallar la interacción cronológica entre los diferentes objetos y componentes del sistema, tanto internos como externos. Al utilizar diagramas de secuencia, se visualiza el orden preciso de los mensajes intercambiados entre la interfaz de usuario, los controladores de la API, los servicios de negocio y la base de datos, incluyendo la comunicación con sistemas externos.

#figure(
  image("../../src/images/Login de Usuario.png"),
  caption: "Diagrama de Secuencia: Login de Usuario. Nota. Este diagrama ilustra el flujo de mensajes entre la interfaz de usuario, los controladores del *backend*, los servicios y la base de datos para autenticar las credenciales de un usuario y generar un token de acceso. Elaboración propia."
)

El proceso de Login de Usuario permite validar la identidad de cada actor. El diagrama de secuencia asociado ilustra detalladamente el flujo de mensajes que se inicia cuando un usuario ingresa sus credenciales en la interfaz. La solicitud viaja al controlador de autenticación del *backend*, que a su vez interactúa con un servicio de usuarios para validar las credenciales contra la base de datos. Si la autenticación es exitosa, se genera un *token* de acceso (como un JWT) a través de un servicio de seguridad, el cual es devuelto a la interfaz para permitir el acceso.

#figure(
  image("../../src/images/Subida de Recurso Didáctico.png"),
  caption: "Diagrama de Secuencia: Subida de Recurso Didáctico. Nota. Este diagrama ilustra el flujo de mensajes cuando un docente carga material didáctico al LMS, mostrando cómo el archivo y sus metadatos son procesados, almacenados externamente y registrados en la base de datos. Elaboración propia."
)

La funcionalidad de Subida de Recurso Didáctico permite que los cursos cuenten con materiales de aprendizaje, y su secuencia de actividades concluye cuando el docente carga contenido al LMS. El proceso comienza cuando el docente selecciona un archivo y sus metadatos desde la interfaz de usuario. Esta información es enviada a un controlador de contenido en el *backend*, que se encarga de delegar el almacenamiento del archivo a un servicio de almacenamiento especializado (como S3 o una solución en la nube). Una vez que el archivo ha sido guardado externamente, el sistema registra la URL y los metadatos en la base de datos del LMS.

#figure(
  image("../../src/images/Selecciona Curso a Inscribir.png"),
  caption: "Diagrama de Secuencia: Inscripción en un Curso con Verificación de Prerrequisitos. Nota. Este diagrama ilustra el flujo de mensajes cuando un estudiante se inscribe en un curso, incluyendo la validación de prerrequisitos y la gestión del proceso de pago antes de registrar la inscripción en la base de datos. Elaboración propia."
)

La Inscripción en un Curso con Verificación de Prerrequisitos detalla la interacción que ocurre cuando un estudiante busca matricularse en un curso que requiere condiciones previas. La secuencia muestra cómo la interfaz de usuario envía la solicitud al controlador de inscripciones, el cual consulta los servicios de curso y progreso para validar el cumplimiento de los prerrequisitos y determinar si el curso es de pago. Dependiendo de estas verificaciones, se inicia el proceso de pago si es necesario, o se registra directamente en la base de datos.

#figure(
  image("../../src/images/Notificación Automática.png"),
  caption: "Diagrama de Secuencia: Envío de Notificación Automática (Nueva Calificación). Nota. Este diagrama ilustra el flujo de mensajes cuando una nueva calificación se registra, desencadenando una notificación automática que se personaliza y envía al estudiante a través de un servicio externo, y se registra en la base de datos del LMS. Elaboración propia."
)

El Envío de Notificación Automática, ejemplificado por una nueva calificación, ilustra un proceso interno para la comunicación proactiva del LMS. Una vez que el servicio de calificaciones registra una nota, este dispara una solicitud al servicio de notificaciones. Este último consulta las plantillas y los datos del usuario en la base de datos para personalizar el mensaje, y luego utiliza una API externa de correo o SMS para enviar la notificación al LMS.

== Modelado de Estructura del Sistema
Con la lógica de negocio y el comportamiento dinámico del LMS ya definidos a través de los casos de uso y los diagramas de actividad y secuencia, es necesario poder desarrollar la comprensión de la estructura estática del sistema para poder visualizar las entidades principales del sistema, sus atributos, y las relaciones  que las conectan. 

#figure(
  image("../../src/images/Gestión Core y Oferta Académica.png"),
  caption: "Diagrama de Clases: Gestión Core y Oferta Académica. Nota. Este diagrama ilustra las entidades clave para la gestión de usuarios, roles, la estructura de la oferta educativa. Elaboración propia."
)

Este diagrama de clases detalla las entidades fundamentales que rigen a los usuarios y sus roles, asegurando un control de acceso preciso y una administración flexible de permisos. Simultáneamente, define la oferta educativa, incluyendo la estructura de modelos educativos, perfiles curriculares, competencias, planes de estudio y los cursos ofertados. Asimismo, integra las clases relacionadas con la programación y los recursos, como los períodos académicos, asignaciones de horario, espacios físicos y plataformas virtuales (Ver Figura 18).

Por otro lado, un componente central es la gestión de contenido, que abarca la administración de recursos didácticos y su vinculación a los módulos de cada curso. Adicionalmente, se incluyen las entidades para las evaluaciones y calificaciones, el sistema de notificaciones, los reportes y auditoría para el monitoreo del sistema, y finalmente, las clases para la gestión financiera que procesan planes de precios y transacciones (Ver Figura 19).

#figure(
  image("../../src/images/Operaciones y Contenido del Curso.png"),
  caption: "Diagrama de Clases: Operaciones y Contenido del Curso. Nota. Este diagrama complementa el modelo de clases, enfocándose en la gestión de contenido, evaluaciones y calificaciones. Elaboración propia."
)

== Requisitos Funcionales (RF)
Los requisitos funcionales articulan las capacidades operativas específicas que el LMS debe entregar a sus usuarios, directamente derivados de la lógica de negocio y los flujos de trabajo identificados en los diagramas de actividad y secuencia. Estos requisitos se han extraído sistemáticamente de las tareas, objetos de datos, compuertas y carriles de los subprocesos BPMN clave: "Diseño de Modelos Educativos", "Gestión Operativa de Planes de AEP" y "Ejecución del Proceso Educativo". 

- *RF1: Gestión de Modelos Educativos:*
  - *RF1.1:* Creación y Edición de Modelos Educativos: El sistema deberá proporcionar funcionalidades al Administrador de Gestión Académica para crear, modificar y mantener Diseños de Modelos Educativos, incluyendo la definición de perfiles curriculares y pedagógicos.
  - *RF1.2:* Registro de Modelos Operativos: El sistema deberá formalizar y registrar los Modelos Operativos Registrados en una base de datos (DB_Sistema_Academico), asignando códigos unívocos y configurando parámetros curriculares.
  - *RF1.3:* Gestión de Requerimientos Operativos Consolidados: El sistema deberá almacenar y permitir la consulta eficiente de los Requerimientos Operativos Consolidados derivados de la revisión y validación de los modelos educativos.

- *RF2: Gestión de Horarios y Espacios:*
  - *RF2.1:* Generación y Gestión de Cronogramas: El sistema deberá permitir la creación, edición y consulta de Cronogramas Propuestos por Modelo, asignando franjas horarias y espacios (físicos o virtuales) a cada componente del plan educativo.
  - *RF2.2:* Verificación de Viabilidad de Horarios/Recursos: El sistema deberá realizar validaciones automáticas sobre la disponibilidad de recursos (docentes, aulas, equipos) durante la generación o modificación de horarios, y deberá proponer soluciones a conflictos detectados.
  - *RF2.3:* Gestión de Conflictos de Asignación: El sistema deberá identificar, permitir la gestión y la resolución de conflictos en la asignación de estudiantes a módulos y docentes a cursos a nivel de facultad, garantizando una AEP Coherente a Nivel de Facultad.

- *RF3: Gestión de Recursos (MTR) y Demandas:*
  - *RF3.1:* Consolidación de Demandas de Recursos: El sistema deberá recopilar y consolidar las Demandas Consolidadas de Recursos (Materiales, Tiempo, Recursos Humanos) requeridas para la operación educativa.
  - *RF3.2:* Registro de Planes Operativos Finales: El sistema deberá registrar los Planes Educativos Operacionales en la DB_Planificacion_Operativa_AEP como el plan operativo final de la institución.

- *RF4: Gestión de la Matrícula y la Oferta:*
  - *RF4.1:* Configuración del Proceso de Matrícula: El sistema deberá permitir la configuración de la oferta académica para el proceso de matrícula, incluyendo la carga de cursos, fechas y reglas de prerrequisitos, asegurando la disponibilidad de Sistemas de Matrícula y Plataformas Listos.
  - *RF4.2:* Gestión del Proceso de Matrícula Estudiantil: El sistema deberá permitir a los estudiantes matricularse en cursos, validar prerrequisitos y disponibilidad, y registrar la Matrícula Estudiantil Confirmada en la DB_Sistema_Matrícula.
  - *RF4.3:* Poblamiento de Colecciones de Cursos: El sistema deberá permitir la configuración final y el ingreso de los datos de los cursos y módulos para generar las Colecciones de Cursos Completadas.

- *RF5: Gestión de la Enseñanza y el Aprendizaje:*
  - *RF5.1:* Asignación de Recursos Didácticos: El sistema deberá asignar Recursos Tecnológicos y Didácticos Asignados (aulas virtuales, materiales de curso, licencias) a los docentes de acuerdo con sus cursos.
  - *RF5.2:* Gestión de Listas de Clase: El sistema deberá proporcionar a los docentes Listas de Clase y Horarios Finales de los estudiantes matriculados.
  - *RF5.3:* Registro de Asistencia: El sistema deberá permitir a los docentes registrar los Registros de Asistencia Actualizados de los estudiantes a las sesiones.
  - *RF5.4:* Gestión de Evaluaciones y Calificaciones: El sistema deberá soportar el diseño, la aplicación, la calificación y el registro de Evaluaciones Realizadas y Calificaciones Registradas de los estudiantes.

#pagebreak()
- *RF6: Soporte y Comunicación:*
  - *RF6.1:* Notificaciones de Matrícula: El sistema deberá enviar Notificación de Matrícula a los estudiantes sobre el proceso de inscripción.
  - *RF6.2:* Gestión de Consultas y Soporte: El sistema deberá permitir a los estudiantes y docentes enviar consultas y recibir Resoluciones de Soporte Generadas (soporte técnico y académico).

- *RF7: Reportes y Análisis:*
  - *RF7.1:* Generación de Reportes Académicos: El sistema deberá generar Reportes de Desempeño Académico consolidados sobre el periodo académico, incluyendo calificaciones y asistencia.

== Requisitos No Funcionales (RNF)
Los requisitos no funcionales definen las cualidades inherentes al sistema y las restricciones operacionales bajo las cuales debe operar. Estos requisitos permiten garantizar la calidad, el rendimiento, la seguridad y la viabilidad a largo plazo del LMS, complementando las funcionalidades principales con atributos de sistema indispensables.

- *RNF1: Rendimiento:*
  - *RNF1.1:* Tiempos de Respuesta: El sistema deberá responder a las solicitudes de matrícula y consulta de notas en menos de 3 segundos para el 95% de las transacciones durante los periodos de picos de carga.
  - *RNF1.2:* Usuarios Concurrentes: El sistema deberá soportar un mínimo de 5000 estudiantes y 500 docentes concurrentes durante los periodos de matrícula y carga académica, sin experimentar una degradación significativa del rendimiento.

- *RNF2: Seguridad:*
  - *RNF2.1:* Autenticación Robusta: El sistema deberá implementar un mecanismo de autenticación segura, incluyendo preferentemente doble factor de autenticación (2FA), para todos los usuarios.
  - *RNF2.2:* Autorización Basada en Roles: El acceso a las funcionalidades y datos deberá estar estrictamente basado en el rol asignado al usuario (Estudiante, Docente, Administrador de Gestión Académica, etc.), garantizando que solo se acceda a la información y funciones explícitamente autorizadas.
  - *RNF2.3:* Protección de Datos Sensibles: La información personal de estudiantes y las calificaciones deberán estar cifradas tanto en tránsito como en reposo, cumpliendo rigurosamente con las normativas de protección de datos locales, como la Ley de Protección de Datos Personales en Perú.
  - *RNF2.4:* Auditoría de Acceso: El sistema deberá registrar exhaustivamente todos los accesos y modificaciones a datos críticos con fines de auditoría y cumplimiento.

- *RNF3: Usabilidad:*
  - *RNF3.1:* Interfaz Intuitiva: La interfaz de usuario deberá ser clara, consistente y fácil de navegar para usuarios con diversos niveles de habilidad tecnológica.
  - *RNF3.2:* Accesibilidad: El sistema deberá cumplir con los estándares de accesibilidad (ej., WCAG 2.1 Nivel AA) para asegurar su uso por personas con discapacidades.
  - *RNF3.3:* Mensajes de Error Claros: El sistema deberá proporcionar mensajes de error informativos y sugerir acciones correctivas cuando ocurran fallos.

- *RNF4: Fiabilidad y Disponibilidad:*
  - *RNF4.1:* Disponibilidad del Servicio: El sistema deberá mantener una disponibilidad del 99.9% del tiempo, excluyendo las ventanas de mantenimiento planificadas.
  - *RNF4.2:* Respaldo y Recuperación: Se deberán implementar procedimientos de respaldo diario de la base de datos y un plan de recuperación ante desastres con un RTO (Recovery Time Objective) de X horas y un RPO (Recovery Point Objective) de Y horas.

- *RNF5: Escalabilidad:*
  - *RNF5.1:* Crecimiento de Usuarios: El sistema deberá ser capaz de escalar para soportar un incremento del 20% anual en el número de estudiantes y cursos durante los próximos 5 años, sin requerir una re-arquitectura mayor.
  - *RNF5.2:* Volúmenes de Datos: La arquitectura deberá permitir el almacenamiento y procesamiento eficiente de grandes volúmenes de datos académicos históricos.

#pagebreak()
- *RNF6: Integración:*
  - *RNF6.1:* Integración con Sistemas Existentes: El sistema deberá proveer APIs o mecanismos de integración robustos para conectar con sistemas institucionales existentes (ej., sistema financiero para pagos de matrícula, directorio de usuarios LDAP/Active Directory, sistema de RRHH para información de docentes).
  - *RNF6.2:* Interoperabilidad con Plataformas Educativas: El sistema deberá ser capaz de integrarse con estándares de interoperabilidad educativa (ej., LTI, SCORM) para la comunicación fluida con herramientas de terceros.

- *RNF7: Mantenibilidad:*
  - *RNF7.1:* Modularidad: El diseño del sistema deberá ser modular para facilitar el mantenimiento, las actualizaciones y la adición de nuevas funcionalidades con un impacto mínimo.
  - *RNF7.2:* Documentación: El código fuente y la arquitectura del sistema deberán estar adecuadamente documentados, facilitando la comprensión y futuras intervenciones.

- *RNF8: Portabilidad:*
  - *RNF8.1:* Compatibilidad con Navegadores: El sistema deberá ser compatible con las últimas dos versiones principales de los navegadores web más utilizados (Chrome, Firefox, Edge, Safari).
  - *RNF8.2:* Acceso Móvil: La interfaz deberá ser responsiva y completamente accesible desde dispositivos móviles (teléfonos y tabletas).

#pagebreak()