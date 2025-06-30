#import "../src/lib.typ": *

#set text(font: "Carlito", lang: "es")
#show: skilling-presentation.with(
  title: "Diseño e Implementación de un Sistema de Gestión del Aprendizaje (LMS) Interactiva para la Optimización Dinámica del Proceso Educativo Superior Peruano",
  authors: (
    // "Aquino Fernandez, Angie Lorena",
    // "Fournier Soto, Fabrizio Eduardo",
    // "Marcano Abreu, Jesús Francisco",
    // "Rios Villegas, Diego Alessandro",
    "Saavedra Guisvert, Natalia Dessyre",
    "Ortiz Herrera, Ana Paula",
    "Gallegos Yanarico, Jarem Joseph",
  ),
  date: datetime.today().display("[day] [month repr:long] [year]"),
  course: "Experiencias Formativas en Situaciones Reales de Trabajo V",
  // course: "Desarrollo de Aplicaciones Web II",
  educational-center: "CIBERTEC",
  department: "Tecnologías de la Información & Ingeniería",
  // department: "Tecnologías de la Información",
  department-full-title: "Computación e Informática - Industrial y Sistemas",
  // department-full-title: "Computación e Informática",
  address-i: "Av. Porongoche 500, Paucarpata",
  address-ii: "(054) 603-535",
  department-website: "www.cibertec.edu.pe",
  teacher: "Veliz Colqui, Roel Zosimo",
  // teacher: "Alpaca Rendon, Jesús Antonio",
  code: "4911",
  // code: "4697",
  cicle: "Sexto Ciclo",
  before: (
    content: include "preface/contents.typ",
    readers-guide: include "preface/readers-guide.typ"
  )
)

#include "documents/introduccion-proyecto.typ"
#include "documents/metodologia-analisis.typ"
#include "documents/arquitectura-diseño.typ"
#include "documents/implementacion-operacion.typ"
#include "documents/demostracion-resultados.typ"
#include "documents/proximos-pasos.typ"

#pagebreak()