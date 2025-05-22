#let natural-image(..args) = style(styles => {
  let (width, height) = measure(image(..args), styles)
  image(..args, width: width, height: height)
})

#let get-spacing-zero-if-first-on-page(default_spacing, heading_location, content_page_margin_full_top, enable: true) = {
  if not enable {
    return (default_spacing, false)
  }
  let heading_is_first_on_page = heading_location.position().y <= content_page_margin_full_top
  
  if heading_is_first_on_page {
    return (0mm, true)
  } else {
    return (default_spacing, false)
  }
}

#let check-font-exists(font-name) = {
  let measured = measure[
    #text(font: font-name, fallback: false)[Texto Test]
  ]

  if measured.width == 0pt [
    #rect(stroke: (paint: red), radius: 1mm, inset: 1.5em, width: 100%)[
      #set text(fallback: true)
      #set heading(numbering: none)
      #show link: it => underline(text(blue)[#it])
      === Error - No se pudo encontra el Font "#font-name"
      Por favor instalar el font requerido "#font-name".
    ]
  ]
}