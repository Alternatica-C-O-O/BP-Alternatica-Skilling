= Lógica de Negocio y Casos de Uso

== Modelado de Negocio 

== Casos de Uso

== Secuencias de Interacción

== Requisitos Funcionales (RF)
Los requisitos funcionales describen las funciones y servicios que el sistema debe proporcionar a los usuarios. Los extraeremos directamente de las tareas, objetos de datos, compuertas y carriles de los subprocesos de BPMN: "Diseño de Modelos Educativos", "Gestión Operativa de Planes de AEP" y "Ejecución del Proceso Educativo".

- *RF1: Gestión de Modelos Educativos:*
  - *RF1.1:* Creación y Edición de Modelos Educativos: El sistema debe permitir al Administrador de Gestión Académica crear, modificar y mantener Diseños de Modelos Educativos, incluyendo perfiles curriculares y pedagógicos.
  - *RF1.2:* Registro de Modelos Operativos: El sistema debe formalizar y registrar los Modelos Operativos Registrados en una base de datos (DB_Sistema_Academico), asignando códigos y configurando parámetros curriculares.
  - *RF1.3:* Gestión de Requerimientos Operativos Consolidados: El sistema debe almacenar y permitir la consulta de los Requerimientos Operativos Consolidados derivados de la revisión de modelos educativos.

- *RF2: Gestión de Horarios y Espacios:*
  - *RF2.1:* Generación y Gestión de Cronogramas: El sistema debe permitir la creación, edición y consulta de Cronogramas Propuestos por Modelo, asignando franjas horarias y espacios (físicos/virtuales) a los componentes del plan educativo.
  - *RF2.2:* Verificación de Viabilidad de Horarios/Recursos: El sistema debe realizar validaciones automáticas sobre la disponibilidad de recursos (docentes, aulas, equipos) al generar o modificar horarios y proponer soluciones a conflictos.
  - *RF2.3:* Gestión de Conflictos de Asignación: El sistema debe identificar y permitir la gestión y resolución de conflictos en la asignación de estudiantes a módulos y docentes a cursos a nivel de facultad, asegurando la AEP Coherente a Nivel de Facultad.

- *RF3: Gestión de Recursos (MTR) y Demandas:*
  - *RF3.1:* Consolidación de Demandas de Recursos: El sistema debe recopilar y consolidar las Demandas Consolidadas de Recursos (Materiales, Tiempo, Recursos Humanos) necesarias para la operación educativa.
  - *RF3.2:* Registro de Planes Operativos Finales: El sistema debe registrar los Planes Educativos Operacionales en la DB_Planificacion_Operativa_AEP como el plan operativo final de la institución.

- *RF4: Gestión de la Matrícula y la Oferta:*
  - *RF4.1:* Configuración del Proceso de Matrícula: El sistema debe permitir la configuración de la oferta académica para el proceso de matrícula, incluyendo la carga de cursos, fechas y reglas de prerrequisitos, asegurando que Sistemas de Matrícula y Plataformas Listos estén disponibles.
  - *RF4.2:* Gestión del Proceso de Matrícula Estudiantil: El sistema debe permitir a los estudiantes matricularse en cursos, validar prerrequisitos y disponibilidad, y registrar la Matrícula Estudiantil Confirmada en la DB_Sistema_Matrícula.
  - *RF4.3:* Poblamiento de Colecciones de Cursos: El sistema debe permitir la configuración final y el ingreso de los datos de los cursos y módulos para generar las Colecciones de Cursos Completadas.

- *RF5: Gestión de la Enseñanza y el Aprendizaje:*
  - *RF5.1:* Asignación de Recursos Didácticos: El sistema debe asignar Recursos Tecnológicos y Didácticos Asignados (aulas virtuales, materiales de curso, licencias) a los docentes según sus cursos.
  - *RF5.2:* Gestión de Listas de Clase: El sistema debe proporcionar a los docentes Listas de Clase y Horarios Finales de los estudiantes matriculados.
  - *RF5.3:* Registro de Asistencia: El sistema debe permitir a los docentes registrar la Registros de Asistencia Actualizados de los estudiantes a las sesiones.
  - *RF5.4:* Gestión de Evaluaciones y Calificaciones: El sistema debe soportar el diseño, aplicación, calificación y registro de Evaluaciones Realizadas y Calificaciones Registradas de los estudiantes.

- *RF6: Soporte y Comunicación:*
  - *RF6.1:* Notificaciones de Matrícula: El sistema debe enviar Notificación de Matrícula a los estudiantes sobre el proceso de inscripción.
  - *RF6.2:* Gestión de Consultas y Soporte: El sistema debe permitir a los estudiantes y docentes enviar consultas y recibir Resoluciones de Soporte Generadas (técnico y académico).

- *RF7: Reportes y Análisis:*
  - *RF7.1:* Generación de Reportes Académicos: El sistema debe generar Reportes de Desempeño Académico consolidados sobre el periodo académico, incluyendo calificaciones y asistencia.

== Requisitos No Funcionales (RNF)
Estos requisitos definen las cualidades del sistema y las restricciones bajo las cuales debe operar.

- *RNF1: Rendimiento:*
  - *RNF1.1:* Tiempos de Respuesta: El sistema deberá responder a las solicitudes de matrícula y consulta de notas en menos de 3 segundos para el 95% de las transacciones durante picos de carga.
  - *RNF1.2:* Usuarios Concurrentes: El sistema deberá soportar al menos X usuarios concurrentes (ej., 5000 estudiantes y 500 docentes) durante los periodos de matrícula y carga académica sin degradación significativa del rendimiento.

- *RNF2: Seguridad:*
  - *RNF2.1:* Autenticación Robusta: El sistema deberá implementar un mecanismo de autenticación seguro (ej., doble factor de autenticación) para todos los usuarios.
  - *RNF2.2:* Autorización Basada en Roles: El acceso a las funcionalidades y datos deberá estar estrictamente basado en el rol del usuario (Estudiante, Docente, Administrador de Gestión Académica, etc.), garantizando que solo se acceda a la información y funciones autorizadas.
  - *RNF2.3:* Protección de Datos Sensibles: La información personal de estudiantes y calificaciones deberá estar cifrada tanto en tránsito como en reposo, cumpliendo con las normativas de protección de datos locales (ej., Ley de Protección de Datos Personales en Perú).
  - *RNF2.4:* Auditoría de Acceso: El sistema deberá registrar todos los accesos y modificaciones a datos críticos con fines de auditoría.

- *RNF3: Usabilidad:*
  - *RNF3.1:* Interfaz Intuitiva: La interfaz de usuario deberá ser clara, consistente y fácil de navegar para usuarios con diferentes niveles de habilidad tecnológica.
  - *RNF3.2:* Accesibilidad: El sistema deberá cumplir con estándares de accesibilidad (ej., WCAG 2.1 AA) para asegurar su uso por personas con discapacidades.
  - *RNF3.3:* Mensajes de Error Claros: El sistema deberá proporcionar mensajes de error informativos y acciones correctivas cuando ocurran fallos.

- *RNF4: Fiabilidad y Disponibilidad:*
  - *RNF4.1:* Disponibilidad del Servicio: El sistema deberá estar disponible el 99.9% del tiempo (excluyendo ventanas de mantenimiento planificadas).
  - *RNF4.2:* Respaldo y Recuperación: Se deberán implementar procedimientos de respaldo diario de la base de datos y un plan de recuperación ante desastres con un RTO (Recovery Time Objective) de X horas y un RPO (Recovery Point Objective) de Y horas.

- *RNF5: Escalabilidad:*
  - *RNF5.1:* Crecimiento de Usuarios: El sistema deberá ser capaz de escalar para soportar un incremento del 20% anual en el número de estudiantes y cursos durante los próximos 5 años sin requerir una re-arquitectura mayor.
  - *RNF5.2:* Volúmenes de Datos: La arquitectura deberá permitir el almacenamiento y procesamiento eficiente de grandes volúmenes de datos académicos históricos.

- *RNF6: Integración:*
  - *RNF6.1:* Integración con Sistemas Existentes: El sistema deberá proveer APIs o mecanismos de integración para conectar con sistemas institucionales existentes (ej., sistema financiero para pagos de matrícula, directorio de usuarios LDAP/Active Directory, sistema de RRHH para información de docentes).
  - *RNF6.2:* Interoperabilidad con Plataformas Educativas: El sistema debe ser capaz de integrarse con estándares de interoperabilidad educativa (ej., LTI, SCORM) para la comunicación con herramientas de terceros.

- *RNF7: Mantenibilidad:*
  - *RNF7.1:* Modularidad: El diseño del sistema deberá ser modular para facilitar el mantenimiento, las actualizaciones y la adición de nuevas funcionalidades.
  - *RNF7.2:* Documentación: El código y la arquitectura del sistema deberán estar adecuadamente documentados.

- *RNF8: Portabilidad (opcional, según estrategia):*
  - *RNF8.1:* Compatibilidad con Navegadores: El sistema deberá ser compatible con las últimas dos versiones principales de los navegadores web más utilizados (Chrome, Firefox, Edge, Safari).
  - *RNF8.2:* Acceso Móvil: La interfaz deberá ser responsiva y accesible desde dispositivos móviles.

#pagebreak()