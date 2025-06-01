#import "../src/lib.typ": *

#set text(font: "Carlito", lang: "es")
#show: skilling-lifecycle.with(
  title: "",
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