= Arquitectura del Sistema
La arquitectura del sistema presentado se ha diseñado meticulosamente para garantizar que la plataforma sea escalable, robusta, mantenible y eficiente, capaz de soportar las demandas de una institución educativa moderna y de alto rendimiento. Basada en un enfoque de microservicios, esta arquitectura busca la modularidad y la independencia de componentes, permitiendo un desarrollo ágil, despliegues continuos y una gestión de recursos optimizada. Cada servicio es autónomo, facilitando la especialización tecnológica y la tolerancia a fallos. Esta sección detallará la visión general de cómo se interconectan los diversos componentes y la infraestructura subyacente que los sustentará, asegurando la entrega de una experiencia de aprendizaje en línea de alta calidad y disponibilidad.

== Visión Arquitectónica General
El diseño arquitectónico presentado (Ver Figura 20) ilustra la visión general del Sistema de Gestión de Aprendizaje (LMS), cimentada en un diseño de microservicios. Este diagrama de alto nivel presenta cómo los diferentes componentes interactúan para ofrecer una plataforma escalable y resiliente.

De esta manera, se trata de plasmar el flujo de cómo los estudiantes y docentes acceden al sistema a través del API Gateway, componente que actúa como el único punto de entrada para todas las solicitudes externas, encargado de enrutarlas a los microservicios apropiados, manejar la seguridad (autenticación y autorización inicial) y la gestión del tráfico.

#figure(
  image("../../src/images/Arquitectura Backend Microservicio.png"),
  caption: "Visión General de la Arquitectura Backend Basada en Microservicios del LMS.Nota. Este diagrama ilustra la interconexión de los componentes clave de la arquitectura de microservicios, mostrando el flujo de solicitudes y la interacción entre el API Gateway, los servicios de infraestructura y los servicios de dominio. Elaboración propia."
)

Detrás del API Gateway, se observan dos componentes de infraestructura críticos:

- *El Eureka Server*, funciona como un Servicio de Descubrimiento. Aquí, cada microservicio se registra al iniciar y notifica su disponibilidad, permitiendo que otros servicios lo encuentren dinámicamente sin necesidad de configuraciones estáticas.

- *El Config Server*, un Servicio de Configuración Centralizada. Este componente proporciona una fuente única y consistente para todas las propiedades y configuraciones de los microservicios, permitiendo su actualización en tiempo real sin requerir reinicios o redescarga de los servicios.

#pagebreak()