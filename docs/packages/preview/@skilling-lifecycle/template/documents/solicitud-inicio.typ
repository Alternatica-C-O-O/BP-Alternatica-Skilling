#import "../../src/core/template.typ": template-page, table-contents

#template-page(
  stage: "Inicio",
  type: "Integración",
  project-code: "ALT-SKL-2025",
  project-name: "Learning Management System Skilling",
  title: "Solicitud de Inicio de Proyecto",
  autor: "Gallegos Yanarico, Jarem Joseph",
  owner: "Gallegos Yanarico, Jarem Joseph",
  manager: "Gallegos Yanarico, Jarem Joseph",
  version: "0.0.3",
  confidential: false,
  date: datetime(day: 12, month: 6, year: 2025),
  reviewers: (
    Nombre: (
      "Gallegos Yanarico, Jarem Joseph",
      "Ortiz Herrera, Ana Paula",
      "Rios Tandaypan, Freyser Leodan",
      "Saavedra Guisvert, Natalia Dessyre",
      "Veliz Colqui, Roel Zosimo"
    ),
    Rol: (
      "Product Owner",
      "Desarrollador Principal",
      "Desarrollador Principal",
      "Analista de Requisitos",
      "Asesor Académico"
    ),
    Acción: (
      "Aprueba",
      "Revisa",
      "Revisa",
      "Revisa",
      "Aprueba"
    ),
    Fecha: (
      datetime(day: 16, month: 6, year: 2025).display(),
      datetime(day: 16, month: 6, year: 2025).display(),
      datetime(day: 16, month: 6, year: 2025).display(),
      datetime(day: 16, month: 6, year: 2025).display(),
      datetime(day: 16, month: 6, year: 2025).display(),
    )
  ),
  versions: (
    Revisión: (
      "v0.0.1",
      "v0.0.2",
      "v0.0.3"
    ),
    Fecha: (
      datetime(day: 1, month: 6, year: 2025).display(),
      datetime(day: 8, month: 6, year: 2025).display(),
      datetime(day: 16, month: 6, year: 2025).display(),
    ),
    "Creada Por": (
      "Gallegos Yanarico, Jarem Joseph",
      "Gallegos Yanarico, Jarem Joseph",
      "Gallegos Yanarico, Jarem Joseph",
    ),
    "Breve Descripción de los Cambios": (
      "Creación inicial del documento.",
      "Definición objetivos y alcance.",
      "Detalles técnicos y revisión formal."
    )
  )
)

A continuación, se presenta un resumen ejecutivo de la Solicitud de Inicio de Proyecto, detallando la información clave sobre los responsables, el contexto organizacional, los plazos y la naturaleza del desarrollo. Esta tabla proporciona una visión general concisa de los elementos fundamentales que autorizan y enmarcan la ejecución del proyecto "Learning Management System Skilling".

#table-contents((
  "": (
    "Iniciador",
    "Organización / Unidad",
    "Propietario del Proyecto (PP)",
    "Fecha de Solicitud",
    "Proveedor de Soluciones (PS)",
    "Autoridad que Aprueba",
    "Esfuerzo Estimado (EE)",
    "Fecha Objetivo de Entrega",
    "Tipo de Desarrollo",
  ),
  "Definición": (
    "Jarem Joseph Gallegos Yanarico (Product Owner)",
    "CIBERTEC - Departamento de Tecnologías de la Información & Ingeniería",
    "Jarem Joseph Gallegos Yanarico",
    datetime(day: 16, month: 6, year: 2025).display(),
    "Equipo de Desarrollo (Estudiantes de Experiencias Formativas V)",
    "Veliz Colqui, Roel Zosimo (Asesor Académico)",
    "1 persona-mes (durante el mes de junio), enfocado en prototipo",
    datetime(day: 30, month: 6, year: 2025).display(),
    "Interno"
  )
))

== Contexto / Situación
El proceso educativo superior y especializado en Perú enfrenta desafíos relacionados con el acceso equitativo a recursos de aprendizaje digital y el fortalecimiento de competencias tecnológicas en estudiantes y docentes. La brecha digital, la falta de plataformas interactivas y adaptables a diversas condiciones de conectividad, y la necesidad de optimizar la gestión de contenidos son problemáticas latentes. Este proyecto surge como respuesta a la oportunidad de democratizar el acceso educativo mediante una plataforma de software libre, fomentando la participación activa y el desarrollo de habilidades digitales esenciales para el entorno profesional actual y futuro. Se busca abordar la ineficiencia en la distribución de materiales, la limitada interactividad en los procesos de enseñanza-aprendizaje y la escasa retroalimentación en sistemas actuales.

== Base legal
Este proyecto se alinea con las directrices estratégicas de la institución educativa CIBERTEC en cuanto a la innovación tecnológica aplicada a la educación y el fomento de soluciones de código abierto. Fundamentalmente, responde al Objetivo de Desarrollo Sostenible (ODS) 4 de la Agenda 2030 de la ONU, que busca "garantizar una educación inclusiva, equitativa y de calidad y promover oportunidades de aprendizaje durante toda la vida para todos". Específicamente, se vincula con la meta 4.4 sobre el aumento de las competencias pertinentes para el empleo, el trabajo decente y el emprendimiento, y la meta 4.a sobre la construcción y mejora de entornos de aprendizaje inclusivos y seguros. La iniciativa también se enmarca dentro de las tendencias de la WEB 4.0 y el impulso de la EdTech a nivel nacional e internacional para modernizar los ecosistemas educativos.


== Resultados
El principal resultado esperado de este proyecto es un prototipo funcional de un Sistema de Gestión del Aprendizaje (LMS) interactivo y de software libre. Este prototipo permitirá la administración de contenido educativo y facilitará la interacción activa entre administradores y alumnos. Se espera que este prototipo demuestre la viabilidad de:

- Una plataforma modular y escalable para la gestión integral de cursos.
- Herramientas de colaboración y comunicación efectivas entre usuarios.
- Mecanismos de personalización del aprendizaje adaptativos.
- Soporte para acceso a contenidos en entornos de conectividad limitada (offline/asíncrono).
- Un incremento medible en las competencias digitales de los usuarios que interactúen con el sistema.


== Impacto
La implementación de este prototipo de LMS tendrá un impacto significativo tanto a nivel interno como externo. Internamente, se espera una transformación en los procesos pedagógicos, fomentando metodologías de enseñanza más dinámicas y participativas. El personal docente se beneficiará de una herramienta que simplifica la gestión académica y el seguimiento del progreso estudiantil, mientras que los estudiantes experimentarán una mayor autonomía y acceso a recursos. Culturalmente, se promoverá una cultura de innovación y adaptabilidad tecnológica dentro de la institución. Externamente, el proyecto contribuirá a la democratización del acceso educativo, especialmente para poblaciones con recursos limitados o ubicadas en zonas con infraestructura deficiente. Se mejorará la imagen institucional al posicionarse como pionera en el desarrollo de soluciones educativas de código abierto que abordan desafíos sociales relevantes.


== Criterios de Éxito
Los criterios de éxito al más alto nivel para este proyecto propuesto serán los siguientes:

- *Alcance:* El prototipo funcional del LMS debe implementar exitosamente al menos el 80% de las funcionalidades críticas (gestión de usuarios, cursos, contenidos, evaluaciones básicas) identificadas en los requisitos.

- *Plazo:* El prototipo debe ser implementado en un entorno de prueba antes del 28 de junio de 2025 y el informe de evaluación final entregado antes del 30 de junio de 2025, de acuerdo con el cronograma del proyecto.

- *Costes:* El proyecto debe desarrollarse dentro de los recursos humanos y técnicos estimados (1 persona-mes, infraestructuras de bajo costo/servicios gestionados en capas gratuitas o de bajo consumo).

- *Usabilidad y Percepción:* El prototipo debe alcanzar una puntuación mínima de 68 en la Escala de Usabilidad del Sistema (SUS) durante la evaluación con usuarios piloto y recibir retroalimentación cualitativa positiva sobre su valor para la optimización del proceso educativo.

== Supuestos
En esta etapa inicial, los supuestos clave considerados para la viabilidad del proyecto son:

- *Disponibilidad de Recursos:* Se asume la disponibilidad continua de los miembros del equipo de desarrollo, el acceso a la infraestructura tecnológica necesaria para el entorno de prueba y las cuentas de los servicios cloud (Supabase, Render, Vercel, GCP) con los niveles de acceso requeridos.

- *Colaboración de Stakeholders:* Se asume la participación activa y la retroalimentación oportuna por parte de los estudiantes, docentes y administradores académicos durante las fases de levantamiento de requisitos y evaluación del prototipo.

- *Estabilidad Tecnológica:* Se asume que las tecnologías de código abierto y los servicios PaaS/BaaS seleccionados (Angular, Spring Boot, Supabase, etc.) mantendrán su estabilidad y disponibilidad para el desarrollo y despliegue del prototipo.

- *Conocimiento del Equipo:* Se asume que el equipo de desarrollo posee o adquirirá rápidamente las competencias técnicas necesarias para trabajar con las tecnologías y la arquitectura definidas (monorepo, microservicios, IaC).

- *Relevancia del Problema:* Se asume que la necesidad de democratizar el acceso educativo y fortalecer las competencias digitales es una prioridad constante para las instituciones de educación superior y el contexto socio-educativo peruano.

== Restricciones
Las principales restricciones que pueden influir en el desarrollo y alcance del proyecto incluyen:

- *Tiempo:* El proyecto está limitado a un plazo de un mes (del 1 al 30 de junio de 2025) para el diseño e implementación de un prototipo funcional.

- *Recursos Humanos:* El equipo de desarrollo es de tamaño limitado, lo que implica una priorización estricta de las funcionalidades del prototipo.

- *Presupuesto:* El desarrollo se realizará con un presupuesto muy ajustado, priorizando el uso de software libre y servicios cloud con planes gratuitos o de bajo costo para el prototipo.

- *Alcance del Prototipo:* Debido a las restricciones de tiempo y recursos, el alcance se limita a un prototipo funcional que demuestre las funcionalidades críticas y la viabilidad técnica, sin aspirar a una versión de producción completa.

- *Compatibilidad del Entorno:* El prototipo debe ser compatible con un rango de dispositivos de gama media/baja y adaptarse a escenarios de conectividad limitada.

== Riesgos
Se han identificado los siguientes riesgos de negocio iniciales, que podrían impactar la consecución de los objetivos del proyecto:

- *Baja Adopción por Usuarios:* Riesgo de que docentes y estudiantes no adopten el prototipo debido a una curva de aprendizaje pronunciada o una percepción de baja utilidad.

- *Limitaciones de Conectividad:* A pesar de los esfuerzos de diseño, las limitaciones extremas de conectividad en algunas zonas podrían afectar la experiencia del usuario y la funcionalidad del LMS.

- *Problemas de Rendimiento del Prototipo:* El prototipo podría no cumplir con las expectativas de rendimiento debido a la optimización incompleta o a limitaciones de los servicios gratuitos/de bajo costo.

- *Falta de Soporte Institucional Continuo:* Riesgo de que, tras la fase del prototipo, no se obtenga el apoyo institucional necesario para una posterior implementación a gran escala o una inversión en infraestructura más robusta.

- *Cambios en Requisitos:* Posibilidad de que los requisitos cambien significativamente durante la fase de desarrollo, lo que podría afectar el cronograma o el alcance del prototipo.

- *Dependencia de Tecnologías Específicas:* Si bien el software libre ofrece flexibilidad, la dependencia de la curva de aprendizaje de ciertas tecnologías (ej. Supabase Edge Functions, particularidades de Spring Boot para microservicios) podría generar retrasos.


#pagebreak()