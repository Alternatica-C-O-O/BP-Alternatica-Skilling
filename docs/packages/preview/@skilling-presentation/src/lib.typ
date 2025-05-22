#import "@preview/touying:0.6.1": *

#let cibertec-colors = (
  negro: rgb("#000000"),
  blanco: rgb("#FFFFFF"),
  ultraazul: rgb("#0025AA"),
  verde-lima: rgb("#ACEA3D"),
  rosa-granate: rgb("#EF3A84"),
  azul-cielo-100: rgb("#0A75C4"),
  azul-cielo-80: rgb("#3B91D0"),
  azul-cielo-60: rgb("#6CACDC"),
  azul-cielo-40: rgb("#9DC8E7"),
  azul-cielo-20: rgb("#CEE3F3"),
  azul-zafiro-100: rgb("#181c62"),
  azul-zafiro-80: rgb("#464981"),
  azul-zafiro-60: rgb("#7477A1"),
  azul-zafiro-40: rgb("#A3A4C0"),
  azul-zafiro-20: rgb("#D1D2E0"),
  iris-violeta-100: rgb("#7E3FA8"),
  iris-violeta-80: rgb("#9865B9"),
  iris-violeta-60: rgb("#B18CCB"),
  iris-violeta-40: rgb("#CBB2DC"),
  iris-violeta-20: rgb("#E5D8EE"),
  rojo-fucsia-100: rgb("#C138A0"),
  rojo-fucsia-80: rgb("#CD60B3"),
  rojo-fucsia-60: rgb("#DA88C6"),
  rojo-fucsia-40: rgb("#E6AFD9"),
  rojo-fucsia-20: rgb("#F3D7EC"),
  azul-marino-100: rgb("#23A9C9"),
  azul-marino-80: rgb("#4FBAD4"),
  azul-marino-60: rgb("#7BCBDF"),
  azul-marino-40: rgb("#A7DCE9"),
  azul-marino-20: rgb("#D3EEF4"),
  azul-ártico-100: rgb("#50D1D1"),
  azul-ártico-80: rgb("#73DADA"),
  azul-ártico-60: rgb("#96E3E3"),
  azul-ártico-40: rgb("#B9EDED"),
  azul-ártico-20: rgb("#DCF6F6"),
)

#let slide(title: auto, ..args, content) = touying-slide-wrapper(self => {
  if title != auto {
    self.store.title = title
  }

  let footer(self) = {
    set align(horizon)
    set text(fill: self.colors.negro, size: .8em)
    v(10pt)
    h(3em)
    utils.call-or-display(self, self.store.footer)
    h(1fr)
    strong(context utils.slide-counter.display())
    h(3em)
  }

  self = utils.merge-dicts(self,
    config-page(background: {
      place(left + top, image("../../@skilling-documentation/src/images/CIBERTEC-sede-arequipa.jpg", height: 100%))
    }, footer: footer)
  )

  let body = {
    block(text(size: 20pt, weight: "bold", utils.display-current-heading(level: 2), fill: self.colors.ultraazul), width: 80%)
    show heading: it => {
      block(text(size: 18pt, weight: "bold", it.body, fill: self.colors.ultraazul), width: 80%)
      v(6pt)
    }
    content
    v(1fr)
  }
  touying-slide(self: self, ..args, body)
})

#let title-slide(background-img: image("../../@skilling-documentation/src/images/CIBERTEC-Blanco-Logo.png", height: 100%), ..args, content) = touying-slide-wrapper(self => {
  self = utils.merge-dicts(self, config-page(
    background: {
      place(left + top, background-img)
    },
    margin: (top: 168pt, bottom: 40pt, left: 85pt, right: 180pt)
  ))
  
  let info = self.info + args.named()
  
  let body = {
    set align(bottom)
    block(text(size: 30pt, weight: "bold", info.title, fill: self.colors.ultraazul), height: 57pt)
    v(1fr)
    block(info.author + [ \ ] + info.date.display("[ day ].[ month ].[ year ]"))
  }
  touying-slide(self: self, body)
})

#let new-section-slide(self: none, section) = touying-slide-wrapper(self => {
  counter(heading).step()
  
  let header(self) = {
    set align(center)
    set text(fill: self.colors.blanco, size: 34pt)
    h(680pt)
    context counter(heading).display()
  }

  self = utils.merge-dicts(self, config-page(
    background: {
      place(left + top, image("../../@skilling-documentation/src/images/CIBERTEC-Blanco-Logo.png", height: 100%))
    },
    header: header,
    margin: (top: 4em, bottom: 3.5em, x: 2em)
  ))

  set align(center + horizon)
  let body = {
    block(text(size: 26pt, weight: "bold", utils.display-current-heading(level: 1), fill: self.colors.ultraazul), width: 65%)
  }
  touying-slide(self: self, body)
})

#let focus-slide(body) = touying-slide-wrapper(self => {
  self = utils.merge-dicts(self, config-page(
    fill: self.colors.primarios,
    margin: 2em,
  ))
  
  set text(fill: self.colors.neutro-luminoso, size: 2em)
  touying-slide(self: self, align(horizon + center, body))
})

#let cibertec-theme(footer: none, lang: "es", ..args, body) = {
  set text(size: 16pt, font: "Sanva", lang: lang)
  set par(spacing: 1.8em, leading: 0.75em)
  set list(spacing: .7em)

  show: touying-slides.with(
    config-page(
      paper: "presentation-16-9",
      margin: (top: 95pt, bottom: 5em, left: 30pt, right: 20pt)
    ),
    config-common(
      slide-fn: slide,
      new-section-slide-fn: new-section-slide
    ),
    config-colors(..cibertec-colors),
    config-store(
      title: none,
      footer: footer
    ),
    ..args
  )
  body
}