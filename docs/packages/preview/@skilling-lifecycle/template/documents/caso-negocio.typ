#import "../../src/core/template.typ": template-page, table-contents

#template-page(
  stage: "Inicio",
  type: "Integración",
  project-code: "ALT-SKL-2025",
  project-name: "Learning Management System Skilling",
  title: "Caso de Negocio",
  autor: "Gallegos Yanarico, Jarem Joseph",
  owner: "Gallegos Yanarico, Jarem Joseph",
  manager: "Gallegos Yanarico, Jarem Joseph",
  version: "0.0.1",
  confidential: false,
  date: datetime(day: 16, month: 6, year: 2025), 
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
      "Definición de contexto y necesidades.",
      "Análisis de alternativas y justificación."
    )
  )
)

El presente Caso de Negocio detalla la justificación y viabilidad de implementar un Sistema de Gestión del Aprendizaje (LMS) interactivo, de software libre, para optimizar el proceso educativo superior y especializado. Este documento aborda la problemática actual, los beneficios esperados, las posibles soluciones y el impacto estratégico para la institución. Sirve como la base fundamental para la toma de decisiones y la asignación de recursos para el proyecto.

#table-contents((
  "": (
    "Iniciador",
    "Organización / Unidad",
    "Fecha de Solicitud",
    "Fecha Objetivo de Entrega",
    "Tipo de Desarrollo",
  ),
  "Definición": (
    "Jarem Joseph Gallegos Yanarico (Product Owner)",
    "CIBERTEC - Departamento de Tecnologías de la Información & Ingeniería",
    datetime(day: 16, month: 6, year: 2025).display(),
    datetime(day: 30, month: 6, year: 2025).display(),
    "Interno"
  )
))

== Contexto

La educación superior y especializada en Perú se encuentra en un punto de inflexión, demandando mayor accesibilidad, interactividad y adaptabilidad frente a las limitaciones de infraestructura y conectividad, especialmente fuera de los grandes centros urbanos. La situación actual se caracteriza por la dependencia de plataformas educativas propietarias costosas, una limitada capacidad de personalización pedagógica y una brecha digital persistente que afecta la participación equitativa de estudiantes y docentes en el ecosistema digital. Existe una urgencia considerable para abordar estas deficiencias, ya que impactan directamente la calidad de la educación, la retención estudiantil y la preparación de los futuros profesionales para un mercado laboral cada vez más digitalizado. La pandemia de COVID-19 expuso y acentuó estas debilidades, demostrando la necesidad crítica de herramientas educativas flexibles y robustas.

=== Impacto de la Situación Actual

La situación actual, marcada por las limitaciones de las plataformas existentes y la brecha digital, tiene impactos multifacéticos en la organización:

- *Impacto en la estrategia de la organización:* La falta de una plataforma LMS propia y adaptable limita la capacidad de la institución para innovar en sus modelos pedagógicos, expandir su oferta educativa a segmentos desatendidos y fortalecer su posicionamiento como líder en tecnología educativa. Esto puede frenar la consecución de objetivos estratégicos relacionados con la inclusión digital y la excelencia académica.

- *Impacto en los procesos de negocio:* Los procesos de enseñanza-aprendizaje se ven afectados por la rigidez de las plataformas, la dificultad para gestionar contenidos de forma dinámica, la falta de herramientas integradas para la evaluación y el seguimiento personalizado. Los procesos administrativos relacionados con la matrícula, el control de asistencia y la comunicación con estudiantes y docentes también pueden ser ineficientes.

- *Impacto en las personas:* Docentes y estudiantes experimentan frustración por las limitaciones tecnológicas. Los docentes tienen dificultades para adaptar sus materiales y metodologías, mientras que los estudiantes enfrentan barreras de acceso, baja interactividad y una menor exposición a herramientas digitales relevantes, lo que impacta negativamente en el desarrollo de sus competencias digitales y su motivación.

- *Impacto en el entorno de TI:* La dependencia de soluciones propietarias genera altos costos de licenciamiento y mantenimiento, limita la capacidad de personalización e integración con otros sistemas internos, y aumenta la vulnerabilidad a las políticas y actualizaciones de terceros. El entorno actual no favorece la innovación ni el aprovechamiento de la arquitectura de microservicios y las prácticas DevOps que la institución busca implementar.

*Procesos de negocio afectados:*

#table-contents((
  "Categoría del Proceso": (
    "Políticas",
    "Legislación",
    "Coordinación",
    "Gestión de Programas",
    "Gestión de Subvenciones / Ayudas",
    "Comunicación y difusión (externa)",
    "Comunicación y difusión (interna)",
    "Gestión Estratégica",
    "Gestión Financiera",
    "Contratación - Compras",
    "Gestión de la Documentación",
    "Gestión de Activos",
    "Auditoría",
    "Recursos Humanos",
    "Tecnologías de la Información",
    "Otros"
  ),
  "Si/No": (
    "Sí",
    "No",
    "Sí",
    "Sí",
    "No",
    "Sí",
    "Sí",
    "Sí",
    "No",
    "No",
    "Sí",
    "No",
    "No",
    "Sí",
    "Sí",
    "No"
  )
))

*Análisis Detallado de Procesos de Negocio Afectados:*

#show table.cell.where(y: 0): strong
#set table(
  stroke: (x, y) => if y == 0 {
    (bottom: 0.7pt + black)
  },
  align: (x, y) => (
    if x > 0 { center }
    else { left }
  )
)

#table(
  columns: (1fr, 1fr, 1fr, 1fr, 1fr, 1fr, 1fr),
  align: center,
  table.header(
    text("Categoría del Proceso", size: 9pt),
    text("Dominio", size: 9pt),
    text("Sub-dominio", size: 9pt),
    text("Macro-proceso", size: 9pt),
    text("Proceso", size: 9pt),
    text("Descripción del Impacto", size: 9pt),
    text("Impacto sobre los Usuarios y Propietarios del Proceso", size: 8pt)
  ),
  [#text("Tecnologías de la Información", size: 9pt)],[#text("Desarrollo de \nSistemas", size: 9pt)],[#text("Gestión de \nPlataformas Educativas", size: 9pt)],[#text("Desarrollo y \nMantenimiento", size: 9pt)],[#text("Operación de LMS", size: 9pt)],[#text("Ineficiencia en la gestión de \ncontenido y limitada \ncapacidad de \npersonalización.", size: 9pt)],[#text("Significativo", size: 9pt)],
  [#text("Gestión de \nProgramas", size: 9pt)],[#text("Diseño Curricular", size: 9pt)],[#text("Actualización de Contenidos", size: 9pt)],[#text("Gestión de \nCursos", size: 9pt)],[#text("Adaptación de la Oferta", size: 9pt)],[#text("Dificultad para incorporar rápidamente nuevos recursos y \nmetodologías interactivas.", size: 9pt)],[#text("Moderado", size: 9pt)],
  [#text("Comunicación \ny difusión \n(interna)", size: 9pt)],[#text("Interacción Académica", size: 9pt)],[#text("Feedback y \nColaboración", size: 9pt)],[#text("Comunicación Docente-Alumno", size: 9pt)],[#text("Gestión \nde Foros y \nAnuncios", size: 9pt)],[#text("Herramientas de comunicación limitadas", size: 9pt)],[#text("Moderado", size: 9pt)],
  [#text("Recursos Humanos", size: 9pt)],[#text("Desarrollo Profesional", size: 9pt)],[#text("Capacitación Docente", size: 9pt)],[#text("Habilitación Digital", size: 9pt)],[#text("Uso de \nHerramientas Digitales", size: 9pt)],[#text("Falta de \ncapacitación continua en \nherramientas educativas modernas para docentes.", size: 9pt)],[#text("Moderado", size: 9pt)],
)

=== Impacto sobre Partes Interesadas y Usuarios

La situación actual afecta directamente a *estudiantes, docentes, personal administrativo y a la propia institución CIBERTEC*. Desde la perspectiva del usuario final, el entorno de trabajo actual para el aprendizaje y la enseñanza es subóptimo.

- *Usuarios:* Los usuarios primarios son los estudiantes de educación superior y especializada, y los docentes. Secundariamente, el personal administrativo involucrado en la gestión académica.

- *Sistemas/Plataformas de TI actualmente utilizadas:* La institución puede estar utilizando plataformas LMS comerciales o soluciones genéricas (ej. Google Classroom, Moodle con configuraciones básicas) que, aunque funcionales, carecen de la flexibilidad y las funcionalidades avanzadas necesarias para una optimización dinámica y una experiencia personalizada. También se utilizan herramientas de comunicación genéricas (ej. correo electrónico, grupos de mensajería).

- *Otros sistemas informáticos y necesidades de integración:* Los usuarios emplean software de ofimática (Microsoft Office, Google Workspace), sistemas de gestión de estudiantes (SGA) para matrículas y calificaciones, y posiblemente herramientas de videoconferencia. El nuevo LMS necesitará *integrarse* con el SGA para la sincronización de listas de alumnos y calificaciones, así como con herramientas de comunicación y colaboración para una experiencia unificada. La falta de una plataforma centralizada y bien integrada genera silos de información y esfuerzos duplicados. El entorno actual no es fluido y requiere que los usuarios naveguen entre múltiples aplicaciones para realizar tareas relacionadas con el proceso educativo.

== Interrelaciones e interdependencias
La situación actual no es un problema aislado; está intrínsecamente interconectada con varios aspectos dentro y fuera de la organización:

- *Interrelaciones internas:*

  - *Área Académica:* Influye directamente en la calidad de la enseñanza, la efectividad de las metodologías pedagógicas y la capacidad de la institución para ofrecer programas innovadores y relevantes.
  - *Área de TI:* Impacta en la carga de trabajo del equipo de TI para el soporte de múltiples plataformas, la gestión de licencias y la capacidad de desarrollar soluciones personalizadas.
  - *Área de Gestión:* Afecta la eficiencia en la gestión administrativa de los cursos, la comunicación interna y la recopilación de datos para la toma de decisiones estratégicas.
  - *Área de Recursos Humanos:* Se relaciona con la necesidad de capacitación y desarrollo de competencias digitales para el personal docente y administrativo.

- *Interrelaciones externas:*

  - *Estudiantes y sus Familias:* La calidad y accesibilidad de la plataforma influyen en la satisfacción del estudiante y en la percepción de valor de la institución. Las limitaciones de conectividad en los hogares impactan directamente en la capacidad de los estudiantes para acceder a los recursos.
  - *Mercado Laboral:* La capacidad de la institución para formar profesionales con sólidas competencias digitales está directamente ligada a las herramientas tecnológicas utilizadas.
  - *Competencia Educativa:* Otras instituciones pueden estar invirtiendo en plataformas más avanzadas, lo que podría afectar la competitividad de CIBERTEC.
  - *Políticas Gubernamentales:* Las iniciativas nacionales de digitalización de la educación o las regulaciones de calidad educativa pueden exigir plataformas más robustas y accesibles.

Este análisis posiciona el problema actual del LMS no solo como una deficiencia tecnológica, sino como un obstáculo estratégico que afecta la misión educativa de CIBERTEC y su relación con el ecosistema externo.


== Resultados esperados
Desde una perspectiva empresarial y organizacional, los resultados deseados al implementar este LMS de software libre se traducen en beneficios tangibles para la institución y sus stakeholders:

- *Organización:*

  - *Optimización Operativa:* Mejora en la eficiencia de la gestión académica y administrativa de cursos y estudiantes.
  - *Innovación Educativa:* Fomento de nuevas metodologías pedagógicas y modelos de aprendizaje híbridos o remotos, aumentando la flexibilidad de la oferta educativa.
  - *Sostenibilidad a Largo Plazo:* Reducción de la dependencia de soluciones propietarias y de los costos asociados a licencias y personalizaciones futuras.

- *Recursos Humanos (Docentes y Administrativos):*

  - *Fortalecimiento de Competencias Digitales:* Capacitación y uso efectivo de herramientas avanzadas para la enseñanza y el aprendizaje.
  - *Eficiencia en la Gestión:* Simplificación de tareas administrativas y pedagógicas, liberando tiempo para actividades de mayor valor añadido.
  - *Satisfacción Laboral:* Mejora en la experiencia de trabajo al contar con herramientas más adaptadas a sus necesidades.

- *Activos (Tecnológicos):*

  - *Plataforma Flexible y Propia:* Desarrollo de un activo tecnológico configurable y escalable que se adapta a las necesidades específicas de la institución.
  - *Reducción de Costos:* Disminución progresiva de los gastos operativos relacionados con el software educativo.

- *Reputación:*

  - *Liderazgo en EdTech:* Posicionamiento como una institución innovadora y comprometida con la inclusión digital y la calidad educativa en Perú.
  - *Atracción de Talento:* Mayor atractivo para estudiantes y docentes que buscan un entorno de aprendizaje moderno y tecnológicamente avanzado.

Estos resultados son la consecuencia directa de superar las limitaciones actuales, y su consecución contribuirá a la visión a largo plazo de CIBERTEC.


== Alternativas posibles
Para abordar la situación actual y alcanzar los resultados deseados, se han analizado las siguientes alternativas, incluyendo un análisis DAFO (Fortalezas, Debilidades, Oportunidades, Amenazas) y una evaluación cualitativa de cada una:

=== Alternativa A: No hacer nada
*Descripción General:* Mantener el *status quo* y continuar utilizando las plataformas y metodologías actuales sin realizar una inversión significativa en un nuevo LMS o en la mejora sustancial de las herramientas existentes. Implica seguir gestionando las limitaciones actuales sin una intervención activa para resolverlas.

#table(
  columns: (1fr, 1fr),
  align: left,
  table.header(
    text("Fortalezas", size: 9pt),
    text("Debilidades", size: 9pt),
  ),
  [#text("• No requiere inversión inicial.", size: 9pt)],[#text("• La institución pierde competitividad.", size: 9pt)],
  [#text("• No interrumpe las operaciones actuales.", size: 9pt)],[#text("• La brecha digital se amplía.", size: 9pt)],
  table.hline(),
  [#text("Oportunidades", size: 9pt, weight: "bold")],[#text("Amenazas", size: 9pt, weight: "bold")],
  table.hline(),
  [#text("• Posibilidad de observar el mercado antes de actuar.", size: 9pt)],[#text("• Desmotivación de docentes y alumnos.", size: 9pt)],
  [#text("• Evitar riesgos de implementación.", size: 9pt)],[#text("• Impacto negativo en la calidad educativa.", size: 9pt)],
  [#text("", size: 9pt)],[#text("• Mayores costos a largo plazo (soporte y personalización).", size: 9pt)],
)

*Análisis / Evaluación Cualitativa:* Esta alternativa no es viable a largo plazo. Aunque elimina el riesgo de inversión y cambios a corto plazo, agrava los problemas actuales. La institución perderá competitividad frente a otras que sí invierten en tecnología educativa, resultando en una disminución de la calidad percibida, desmotivación de la comunidad educativa y mayores costos indirectos por ineficiencia. No permite aprovechar las oportunidades de innovación y crecimiento en el ámbito EdTech.

=== Alternativa B: Reutilización de una solución existente (Moodle mejorado/personalizado)
*Descripción General:* Adoptar una plataforma LMS de software libre ya consolidada en el mercado, como Moodle, e invertir en su personalización, integración con sistemas existentes y desarrollo de funcionalidades adicionales para cubrir las necesidades específicas de interactividad y adaptabilidad del proyecto. Esto implica aprovechar una base tecnológica probada.

#table(
  columns: (1fr, 1fr),
  align: left,
  table.header(
    text("Fortalezas", size: 9pt),
    text("Debilidades", size: 9pt),
  ),
  [#text("• Costos de desarrollo iniciales más bajos.", size: 9pt)],[#text("• Limitaciones en personalización profunda de la arquitectura.", size: 9pt)],
  [#text("• Comunidad de soporte amplia.", size: 9pt)],[#text("• Curva de aprendizaje para personalización compleja.", size: 9pt)],
  [#text("• Funcionalidades básicas ya probadas.", size: 9pt)],[#text("• Posible deuda técnica por código heredado.", size: 9pt)],
  table.hline(),
  [#text("Oportunidades", size: 9pt, weight: "bold")],[#text("Amenazas", size: 9pt, weight: "bold")],
  table.hline(),
  [#text("• Implementación más rápida.", size: 9pt)],[#text("• Resistencia al cambio si la interfaz no es intuitiva.", size: 9pt)],
  [#text("• Acceso a plugins y extensiones existentes.", size: 9pt)],[#text("• Dificultad para cumplir requisitos de conectividad baja.", size: 9pt)],
  [#text("• Reducción del riesgo tecnológico.", size: 9pt)],[#text("• Dependencia de la hoja de ruta de la plataforma base.", size: 9pt)],
)

*Análisis / Evaluación Cualitativa:* Esta alternativa es viable y representa una mejora significativa respecto a "No hacer nada". Ofrece una base sólida y reduce el tiempo de desarrollo inicial. Sin embargo, puede enfrentar desafíos en la personalización a nivel de arquitectura (microservicios), lo que podría limitar la optimización dinámica y la adaptabilidad profunda que el proyecto busca. La verdadera democratización y fortalecimiento de competencias digitales pasa por una solución más a medida y controlada. Es una opción de bajo riesgo, pero no la más alineada con la visión a largo plazo de ser pioneros en EdTech.

=== Alternativa C: Desarrollo de una solución sofisticada (LMS de monorepo con microservicios)
*Descripción General:* Desarrollar un Sistema de Gestión del Aprendizaje (LMS) desde cero, siguiendo una arquitectura de monorepo con microservicios (Java/Spring Boot para el backend, Angular para el frontend), utilizando servicios cloud flexibles (Supabase, Render, Vercel, GCP) e implementando Infraestructura como Código (Terraform) y CI/CD (GitHub Actions). Esta solución busca la máxima flexibilidad, escalabilidad, control y optimización.

#table(
  columns: (1fr, 1fr),
  align: left,
  table.header(
    text("Fortalezas", size: 9pt),
    text("Debilidades", size: 9pt),
  ),
  [#text("• Máxima personalización y control.", size: 9pt)],[#text("• Mayores costos y tiempo de desarrollo inicial.", size: 9pt)],
  [#text("• Escalabilidad y resiliencia inherentes a microservicios.", size: 9pt)],[#text("• Mayor complejidad de gestión y operación.", size: 9pt)],
  [#text("• Optimización para conectividad variada.", size: 9pt)],[#text("• Requiere un equipo con alta especialización técnica.", size: 9pt)],
  [#text("• Fomento de competencias digitales avanzadas en el equipo.", size: 9pt)],[#text("• Riesgos tecnológicos y de integración más elevados.", size: 9pt)],
  table.hline(),
  [#text("Oportunidades", size: 9pt, weight: "bold")],[#text("Amenazas", size: 9pt, weight: "bold")],
  table.hline(),
  [#text("• Posicionamiento como líder en EdTech y desarrollo propio.", size: 9pt)],[#text("• Posibles retrasos si no se gestionan bien los riesgos.", size: 9pt)],
  [#text("• Generación de propiedad intelectual y conocimiento interno.", size: 9pt)],[#text("• Sobrecarga inicial de trabajo para el equipo de TI.", size: 9pt)],
  [#text("• Adaptabilidad a futuras necesidades sin depender de terceros.", size: 9pt)],[#text("• Resistencia interna al cambio por la complejidad de la nueva plataforma.", size: 9pt)],
)
*Análisis / Evaluación Cualitativa:* Esta alternativa es altamente viable y la más alineada con la visión a largo plazo del proyecto. Aunque implica una inversión inicial mayor en tiempo y recursos, ofrece un control sin precedentes sobre la funcionalidad, el rendimiento y la escalabilidad. Fomenta el desarrollo de competencias técnicas avanzadas dentro del equipo y permite una solución verdaderamente adaptada a las necesidades específicas de la institución, incluyendo la optimización para baja conectividad. Es la opción que maximiza el impacto estratégico y la democratización educativa a largo plazo, consolidando a CIBERTEC como una entidad innovadora.


=== Alternativa seleccionada: Desarrollo de una solución sofisticada (LMS de monorepo con microservicios)
*Descripción General:* La alternativa seleccionada implica el diseño y desarrollo de un prototipo funcional de un LMS interactivo bajo una arquitectura de monorepo y microservicios. Este enfoque permitirá construir una plataforma altamente personalizada y optimizada para las necesidades del proceso educativo superior y especializado en Perú, incluyendo la capacidad de operar eficientemente en entornos con conectividad limitada. Se integrarán tecnologías de punta como Angular para el frontend, Java/Spring Boot para los microservicios del backend, Supabase como BaaS, y herramientas de DevOps como Docker, Terraform y GitHub Actions para garantizar un desarrollo, despliegue y mantenimiento ágiles y eficientes.

#table(
  columns: (1fr, 1fr),
  align: left,
  table.header(
    text("Fortalezas", size: 9pt),
    text("Debilidades", size: 9pt),
  ),
  [#text("• Control total sobre el desarrollo y la propiedad intelectual.", size: 9pt)],[#text("• Mayor complejidad inicial y curva de aprendizaje.", size: 9pt)],
  [#text("• Alta escalabilidad y rendimiento a medida.", size: 9pt)],[#text("• Requiere inversión significativa en tiempo y expertise.", size: 9pt)],
  [#text("• Adaptabilidad superior a contextos de baja conectividad.", size: 9pt)],[#text("• Riesgo de fallos en integración de múltiples servicios.", size: 9pt)],
  [#text("• Fortalecimiento de capacidades tecnológicas internas.", size: 9pt)],[#text("• Mantenimiento y gobernanza más complejos a largo plazo.", size: 9pt)],
  table.hline(),
  [#text("Oportunidades", size: 9pt, weight: "bold")],[#text("Amenazas", size: 9pt, weight: "bold")],
  table.hline(),
  [#text("• Liderazgo y diferenciación en el sector EdTech.", size: 9pt)],[#text("• Retrasos por desafíos técnicos imprevistos.", size: 9pt)],
  [#text("• Democratización real del acceso a la educación.", size: 9pt)],[#text("• Resistencia al cambio o falta de adopción por usuarios.", size: 9pt)],
  [#text("• Potencial de monetización y expansión futura.", size: 9pt)],[#text("• Dependencia de proveedores de servicios cloud.", size: 9pt)],
)

*Análisis / Evaluación Cualitativa:* La elección de esta alternativa se justifica por su capacidad de ofrecer la solución más completa y estratégica para los desafíos actuales de la educación en Perú. Si bien presenta una mayor complejidad inicial y requiere un equipo con habilidades especializadas, los beneficios a largo plazo en términos de personalización, escalabilidad, resiliencia y el posicionamiento de la institución son superiores. Esta alternativa permite la democratización real del acceso educativo al diseñar específicamente para entornos con conectividad limitada, y fortalece de manera sustancial las competencias digitales tanto de los usuarios finales como del equipo de desarrollo interno. La arquitectura de microservicios y la metodología DevOps aseguran que el proyecto no solo cumpla con los requisitos actuales, sino que también esté preparado para futuras evoluciones y crecimientos.

Para concluir, sobre la base del análisis de alternativas anterior, la solución elegida es el Desarrollo de una solución sofisticada: un LMS de monorepo con microservicios. Esta elección se fundamenta en la convicción de que solo una solución a medida, con un control total sobre su arquitectura y funcionalidades, puede abordar eficazmente las complejas necesidades de la educación superior y especializada en un contexto de conectividad variada. El beneficio de lograr una plataforma altamente adaptable, escalable y que fomente la innovación educativa supera con creces los costos y la complejidad inicial asociada a su desarrollo.

== Descripción de la Solución
En este capítulo se explicará más detalladamente la solución elegida en el epígrafe 4, que es el *Desarrollo de un LMS de monorepo con microservicios*.

=== Base legal
La solución propuesta se alinea con las *políticas institucionales de CIBERTEC* para la innovación educativa y la adopción de tecnologías de vanguardia que impulsen la calidad y la accesibilidad. Se conecta directamente con el *Plan Estratégico Institucional (PEI)*, específicamente en los ejes de *transformación digital* y *expansión de la oferta educativa online/híbrida*. Además, esta iniciativa apoya la adhesión a los principios de *software libre y código abierto*, lo cual es una directriz creciente en la visión a largo plazo de la institución para fomentar la autonomía tecnológica y reducir la dependencia de proveedores externos. Se busca que la solución cumpla con los marcos normativos educativos peruanos en materia de uso de tecnologías en la educación superior.

=== Beneficios
Los principales beneficios de la solución propuesta, entendidos como la mejora cuantificable y la ventaja percibida por los stakeholders, son:

- *Mayor Accesibilidad Educativa:* Permite a estudiantes de diversas regiones, incluyendo aquellas con conectividad limitada, acceder a contenidos y participar activamente en el proceso educativo. (Beneficio Cuantificable: Ampliación de la base de usuarios geográficamente dispersos).

- *Optimización de Procesos Académicos:* Mejora la eficiencia en la gestión de cursos, materiales y evaluaciones para docentes y administradores. (Beneficio Cuantificable: Reducción del tiempo dedicado a tareas administrativas en un 20%).

- *Fomento de la Interacción y Colaboración:* Herramientas integradas que facilitan la comunicación y el trabajo colaborativo entre estudiantes y docentes. (Beneficio Cuantificable: Incremento en la participación en foros y actividades grupales).

- *Desarrollo de Competencias Digitales:* Exposición y uso de una plataforma tecnológicamente avanzada que fortalece las habilidades digitales de toda la comunidad educativa. (Beneficio Cuantificable: Mejora en la autopercepción de competencias digitales de estudiantes y docentes, medido por encuestas SUS).

- *Reducción de Costos a Largo Plazo:* Al ser una solución de software libre y arquitectura modular, minimiza los costos de licenciamiento y permite una evolución más controlada y económica. (Beneficio Cuantificable: Ahorro del 5% en costos de software en 10 años).

- *Flexibilidad y Adaptabilidad:* La arquitectura de microservicios permite adaptar la plataforma a futuras necesidades pedagógicas y tecnológicas sin requerir rediseños completos. (Beneficio Cuantificable: Facilidad y velocidad de implementación de nuevas funcionalidades).

- *Posicionamiento Estratégico:* CIBERTEC se consolida como líder en la adopción e innovación en EdTech en el panorama educativo peruano. (Beneficio Cuantificable: Incremento en la reputación e imagen institucional).

=== Criterios de éxito y/o aceptación
Los criterios de éxito y/o aceptación del proyecto en su conjunto, que permitirán evaluarlo como un éxito o un fracaso, son:

- *Criterios de Éxito del Proyecto (Relacionados con Resultados Esperados:*
  
  - *Funcionalidad del Prototipo:* El 80% de las funcionalidades críticas (gestión de usuarios, cursos, carga de contenido, foros básicos, seguimiento de progreso) definidas para el prototipo deben estar operativas y ser estables.
  - *Rendimiento del Prototipo:* La plataforma debe cargar las páginas principales en un promedio de menos de 3 segundos en condiciones de red estándar.
  - *Costo de Desarrollo:* El desarrollo del prototipo debe mantenerse dentro del presupuesto de esfuerzo humano estimado (1 persona-mes para junio 2025) y utilizar los servicios cloud dentro de sus planes gratuitos/bajos costos.
  - *Fechas Clave:* Entrega del prototipo en entorno de prueba antes del 28 de junio de 2025 y finalización del informe de evaluación antes del 30 de junio de 2025.
  - *Usabilidad del Producto:* El prototipo debe obtener una puntuación SUS (System Usability Scale) de al menos 68 durante las pruebas con usuarios piloto.

- *Criterios Críticos para el Éxito del Proyecto:*
  
  - *Operatividad Básica del LMS:* El sistema debe permitir la creación de cuentas de usuario, la asignación a cursos y la visualización de contenido educativo. Sin estas funcionalidades básicas, el proyecto no cumple su objetivo principal.
  - *Interacción Mínima:* Debe ser posible la comunicación básica (ej. foros o anuncios) entre docentes y alumnos.


=== Alcance
El alcance de la solución propuesta se define en el *diseño y la implementación de un prototipo funcional de un Sistema de Gestión del Aprendizaje (LMS) interactivo*, desarrollado bajo una arquitectura de monorepo y microservicios.

- *Productos:* El producto principal es el prototipo del LMS. Esto incluye:
  - Un módulo frontend (interfaz de usuario para estudiantes y administradores).
  - Un conjunto de microservicios backend (API Gateway, Users, Courses, Enrollment, Notifications, etc.).
  - La base de datos y los servicios de BaaS (Supabase).
  - Configuraciones de infraestructura como código (Terraform).
  - Configuraciones de CI/CD (GitHub Actions).

- *Procesos:* Abarca los procesos de gestión de usuarios, gestión de cursos, matriculación, entrega y consumo de contenidos, y funcionalidades básicas de comunicación y notificación.

- *Organizaciones:* La solución está diseñada principalmente para la *unidad académica y de TI de CIBERTEC*, con un enfoque en los estudiantes y docentes de sus programas de educación superior y especializada. No se contempla una solución común para múltiples instituciones externas en esta fase de prototipo. Sin embargo, su diseño modular y de software libre facilita una futura expansión o replicación.


=== Impacto de la Solución
La solución propuesta abordará de manera directa el impacto negativo identificado en los procesos y en la organización, transformándolos positivamente:

#table(
  columns: (1fr, 1fr),
  align: left,
  table.header(
    "Proceso",
    "Descripción del Impacto"
  ),
  [#text("Tecnologías de la Información", size: 9pt)],[#text("La implementación del LMS propio reducirá la dependencia de proveedores externos, permitiendo mayor control, personalización y eficiencia en la gestión del ecosistema digital educativo. Optimización de la gestión de contenido.", size: 9pt)],
  [#text("Gestión de Programas", size: 9pt)],[#text("Facilitará la adaptación ágil de currículos y la incorporación de metodologías innovadoras y recursos interactivos, mejorando la pertinencia y calidad de la oferta educativa.", size: 9pt)],
  [#text("Comunicación y difusión (interna)", size: 9pt)],[#text("Mejorará la fluidez y efectividad de la comunicación entre docentes, estudiantes y administración a través de herramientas integradas de foros, anuncios y notificaciones.", size: 9pt)],
  [#text("Recursos Humanos", size: 9pt)],[#text("Promoverá el desarrollo y la actualización de competencias digitales en docentes y administrativos, preparándolos para utilizar y maximizar las funcionalidades de la plataforma y otras herramientas digitales.", size: 9pt)],
)
=== Entregables
Los entregables más importantes de la solución propuesta en la fase de prototipo son:

- *Prototipo Funcional del LMS:* Código fuente completo de la aplicación (frontend, microservicios backend) en el monorepo.

- *Base de Datos Inicial:* Esquema de la base de datos (PostgreSQL) y migraciones.

- *Documentación de Arquitectura:* Diagramas, diseño de microservicios, especificaciones técnicas.

- *Documento de Requisitos:* Detalles de las funcionalidades funcionales y no funcionales.

- *Prototipos y Maquetas de UI/UX:* Diseños visuales que representan la experiencia de usuario.

- *Configuraciones de Infraestructura como Código (IaC):* Scripts de Terraform para el aprovisionamiento de recursos.

- *Configuraciones CI/CD:* Flujos de trabajo de GitHub Actions para la automatización de la integración y el despliegue.

- *Informe de Evaluación del Prototipo:* Reporte técnico con resultados de pruebas de funcionamiento y usabilidad (incluyendo análisis SUS).

- *Presentación del Prototipo:* Demostración del prototipo a stakeholders clave.

=== Supuestos
Los supuestos clave para la viabilidad y éxito de la solución propuesta incluyen:

- *Disponibilidad de Expertise:* Se asume que el equipo de desarrollo, aunque con un plazo limitado, tiene o adquirirá la capacidad técnica para implementar la arquitectura de microservicios y manejar las tecnologías seleccionadas.

- *Conectividad de Usuario:* Se asume que, aunque se optimice para baja conectividad, existe un nivel mínimo de acceso a internet para que los usuarios puedan interactuar inicialmente con la plataforma y sincronizar contenido.

- *Soporte de Proveedores Cloud:* Se asume que los servicios PaaS/BaaS (Supabase, Render, Vercel, GCP) mantendrán sus funcionalidades y planes gratuitos/de bajo costo durante la fase de prototipo.

- *Participación Activa de Usuarios Piloto:* Se asume que habrá un grupo de usuarios (estudiantes y docentes) dispuestos a participar activamente en las pruebas de usabilidad y proporcionar retroalimentación constructiva.

- *Aceptación del Código Abierto:* Se asume que la cultura institucional apoya la adopción de soluciones de software libre para su desarrollo y futura evolución.

=== Restricciones
Las principales restricciones que pueden afectar la implementación de la solución son:

- *Plazo de Desarrollo:* Estricto límite de tiempo (junio de 2025) para el desarrollo y entrega del prototipo funcional.

- *Recursos Limitados:* Restricción de personal dedicado y presupuesto, lo que impone una priorización estricta de las funcionalidades del MVP.

- *Interoperabilidad:* La integración con sistemas heredados (ej. SGA existente) puede presentar desafíos y requerir adaptaciones específicas.

- *Complejidad Tecnológica:* La arquitectura de microservicios, aunque beneficiosa, introduce una mayor complejidad en el desarrollo, despliegue y depuración.

- *Requerimientos No Funcionales:* Criterios como la seguridad y la robustez, aunque considerados, estarán en un nivel de prototipo y requerirán mayor inversión en fases futuras.


=== Riesgos
Se han identificado los siguientes riesgos clave de la solución propuesta, junto con estrategias de mitigación de alto nivel:

- *Riesgo: Complejidad Técnica / Curva de Aprendizaje del Equipo.*

  - *Mitigación:* Priorizar la capacitación intensiva en las tecnologías clave (Spring Boot, Angular, Supabase, Terraform); fomentar el trabajo en pares y la revisión de código; documentar ampliamente las decisiones de diseño.

- *Riesgo: Problemas de Integración entre Microservicios.*

  - *Mitigación:* Diseño de API robustas y claras; pruebas de integración continuas y automatizadas desde el inicio del desarrollo; uso de herramientas de monitoreo para identificar cuellos de botella.

- *Riesgo: Rendimiento Insuficiente del Prototipo bajo Carga.*

  - *Mitigación:* Realizar pruebas de carga básicas; optimizar consultas a la base de datos; considerar técnicas de caching y optimización de recursos desde etapas tempranas; planificar la escalabilidad de los servicios cloud para futuras fases.

- *Riesgo: Dificultad en la Gestión del Monorepo.*

  - *Mitigación:* Establecer pautas claras de estructura de código y colaboración; utilizar herramientas de monorepo (ej. Nx, Lerna) si la complejidad lo amerita; implementar revisiones de código estrictas.

- *Riesgo: Fallos en la Automatización de CI/CD.*

  - *Mitigación:* Pruebas exhaustivas de los pipelines de CI/CD en entornos de desarrollo; monitoreo continuo de los flujos de trabajo; establecer un proceso de reversión rápido en caso de fallos de despliegue.


#pagebreak()