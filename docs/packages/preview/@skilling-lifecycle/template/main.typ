#import "../src/lib.typ": *

#set text(font: "Carlito", lang: "es")
#show: skilling-lifecycle.with(
  title: "Diseño e Implementación de un Sistema de Gestión del Aprendizaje (LMS) Interactiva para la Optimización Dinámica del Proceso Educativo Superior Peruano",
  authors: (
    // "Aquino Fernandez, Angie Lorena",
    // "Fournier Soto, Fabrizio Eduardo",
    // "Marcano Abreu, Jesús Francisco",
    // "Rios Villegas, Diego Alessandro",
    "Saavedra Guisvert, Natalia Dessyre",
    "Ortiz Herrera, Ana Paula",
    "Rios Tandaypan, Freyser Leodan",
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

#include "documents/solicitud-inicio.typ"
#include "documents/caso-negocio.typ"
#include "documents/acta-constitucion.typ"
#include "documents/manual-proyecto.typ"
#include "documents/trabajo-proyecto.typ"

#include "documents/implementacion-negocio.typ"
#include "documents/gestion-requisitos.typ"
#include "documents/gestion-riesgos.typ"
#include "documents/gestion-incidentes.typ"
#include "documents/gestion-calidad.typ"

#include "documents/gestion-comunicacion.typ"
#include "documents/acta-reunion.typ"
#include "documents/control-calidad.typ"
#include "documents/control-implementacion.typ"
#include "documents/fin-proyecto.typ"

#pagebreak()