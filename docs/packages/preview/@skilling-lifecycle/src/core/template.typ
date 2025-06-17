#import "@preview/tada:0.2.0": TableData, to-table
#import "@preview/datify:0.1.4": custom-date-format
#import "@preview/showybox:2.0.4": showybox

#let STAGES = (
  "Inicio",
  "Planeación",
  "Ejecución",
  "Monitoreo y Control",
  "Cierre"
) 

#let TYPES = (
  Integración : "#000000",
  Alcance : "#1C2D7C",
  Cronograma : "#139AEA",
  Costo : "#0E7131",
  Calidad : "#F38D90",
  Recurso : "#76BC30",
  Comunicacion : "#C50B21",
  Riesgo : "#E1AD0D",
  Adquisicion : "#F38218",
  Interesado : "#862979"
)

#let template-page(
  stage: str, 
  type: str, 
  project-name: str,
  project-code: str,
  title: str,
  autor: str,
  owner: str,
  manager: str,
  date: datetime,
  version: str,
  confidential: bool,
  reviewers: (:), 
  versions: (:),
  body: none
) = {
  set text(size: 10pt, fill: black)
  set par(leading: 1em, justify: true)

  let header = [
    #align(right, [
      #block([
        #align(right, [
          #text(weight: 540, 1.2em, type, fill: white)
          #v(0.7em, weak: true)
          #text(weight: 540, 1.1em," #" + project-code, fill: white)
          #v(0.7em, weak: true)
          #text(weight: 720, 0.9em, "v" + version + " • " + custom-date-format(date, "DDth MMMM, YYYY", "es"), fill: white)
        ])
      ])
    ])
  ]
  
  table(
    fill: rgb(TYPES.at(type)),
    columns: (1fr, auto ,1fr),
    inset: 10pt,
    align: horizon,
    table.header(
      [#image("../images/CIBERTEC-Blanco-Logo.png", width: 6.5em)],
      [#align(center,text(
        for i in STAGES {
          if i == stage {
            upper(stage)
          }
        }
      , size: 20pt, weight: "bold", fill: white))],
      [#header]
    )
  )

  show table.cell.where(y: 0): strong
  set table(
    stroke: (x, y) => if y == 0 {
      (bottom: 0.7pt + black)
    },
    align: (x, y) => (
      if x > 0 { center }
      else { left }
    )
  )
  table(
    columns: (auto, 1fr),
    inset: 4pt,
    align: horizon,
    fill: (x, y) => if y == 0 {
      gray.lighten(60%)
    } else if (x == 0) {                                                    
      gray.lighten(70%)
    },
    table.header(
      [#text("Descripción")], [#text("Valor")]
    ),
    [#text("Nombre del Documento")], [#text(title)],
    [#text("Nombre del Proyecto")], [#text(project-name + " (#" + project-code + ")")],
    [#text("Autor del Documento")], [#text(autor)],
    [#text("Propietario del Proyecto")], [#text(owner)],
    [#text("Director del Proyecto")], [#text(manager)],
    [#text("Versión del Documento")], [#text("v" + version)],
    [#text("Confidencialidad")], [#text(if confidential == true {"INFORMACIÓN PROPIETARIA - NO DISTRIBUIR"} else {"DE DOMINIO PÚBLICO"})],
    [#text("Fecha")], [#custom-date-format(date, "DDth MMMM, YYYY", "es")],
  )

  text(weight: "bold", 1.2em, "Aprobación y Revisión del Documento", fill: black)
  v(0.8em, weak: true)
  text(weight: 540, 1.0em, "Se requieren todas las aprobaciones. Se deben mantener registros de cada aprobación. Todos los revisores de la" + v(0.7em, weak: true) + "lista se consideran necesarios a menos que se indique explícitamente como Opcionales.")

  let aprobacion = TableData(data: reviewers)
  to-table(
    columns: (1.5fr, 1fr, 0.5fr, 0.5fr),
    inset: 4pt,
    align: horizon,
    fill: (x, y) => if y == 0 {
      gray.lighten(60%)
    },
    aprobacion
  )

  text(weight: "bold", 1.2em, "Historial de Documentos", fill: black)
  v(0.8em, weak: true)
  text(weight: 540, 1.0em, "El Autor del Documento está autorizado a realizar los siguientes tipos de cambios sin requerir una nueva aproba-" + v(0.7em, weak: true) + "ción del documento: 1) Edición, formato y ortografía; 2) Aclaraciones.")
  v(1em, weak: true)
  text(weight: 540, 1.0em, "Para solicitar un cambio en este documento, póngase en contacto con el Autor del Documento o el Propietario del" + v(0.7em, weak: true) + "Proyecto. Las modificaciones de este documento se resumen en la siguiente tabla en orden cronológico inverso" + v(0.7em, weak: true) +"(primero la última versión).")

  let version = TableData(data: versions)
  to-table(
    columns: (auto, auto, 0.35fr, 0.5fr),
    inset: 4pt,
    align: horizon,
    fill: (x, y) => if y == 0 {
      gray.lighten(60%)
    },
    version
  )

  pagebreak()
  heading(title, level: 1)
}

#let table-contents(content) = to-table(
  columns: (auto, 1fr),
  inset: 4pt,
  align: horizon,
  fill: (x, y) => if x == 0 {
    gray.lighten(60%)
  } else if y == 0 {
    gray.lighten(60%)
  },
  TableData(data: content)
)

#let content-document(title: str) = showybox(
  frame: (    
    body-color: black.lighten(20%),
    border-color: black.lighten(20%)
  ),
  body-style: (
    color: white,
    weight: "regular",
    align: left
  ),
  heading(title, level: 2)
)