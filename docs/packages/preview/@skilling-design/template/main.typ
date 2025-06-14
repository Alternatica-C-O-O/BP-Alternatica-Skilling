#import "../src/lib.typ": *

#set text(font: "Carlito", lang: "es")
#show: skilling-design.with(
  title: "Diseño e Implementación de un Sistema de Gestión del Aprendizaje (LMS) Interactiva para la Optimización Dinámica del Proceso Educativo",
  authors: (
    "Aquino Fernandez, Angie Lorena",
    "Fournier Soto, Fabrizio Eduardo",
    "Marcano Abreu, Jesús Francisco",
    "Saavedra Guisvert, Natalia Dessyre",
    "Rios Villegas, Diego Alessandro",
    "Gallegos Yanarico, Jarem Joseph",
  ),
  date: datetime.today().display("[day] [month repr:long] [year]"),
  course: "Desarrollo de Aplicaciones Web II",
  educational-center: "CIBERTEC",
  department: "Tecnologías de la Información",
  department-full-title: "Computación e Informática",
  address-i: "Av. Porongoche 500, Paucarpata",
  address-ii: "(054) 603-535",
  department-website: "www.cibertec.edu.pe",
  teacher: "Alpaca Rendon, Jesús Antonio",
  code: "4697",
  cicle: "Sexto Ciclo",
  before: (
    content: include "preface/contents.typ",
    readers-guide: include "preface/readers-guide.typ"
  )
)