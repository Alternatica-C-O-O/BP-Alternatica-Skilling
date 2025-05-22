#let cibertec_heading_line_thin_stroke = 0.75pt
#let cibertec_header_line_height = 1.2pt
#let cibertec_inner_page_margin_top = 22pt
#let cibertec_title_logo_height = 22mm
#let cibertec_body_line_height = cibertec_header_line_height / 2

#let cibertec_page_margin_title_page = (
  top: 15mm, left: 15mm, right: 15mm, bottom: 15mm - 1mm
)
#let cibertec_page_margin_small = cibertec_page_margin_title_page

#let cibertec_page_margin_big = (
  top: 30mm, left: 31.5mm, right: 31.5mm, bottom: 56mm
)

#let cibertec_page_margin_medium = (
  top: 15mm, left: 25mm, right: 25mm, bottom: 15mm - 1mm
)

#let cibertec_exercise_page_margin = (
  top: 15mm, left: 15mm, right: 15mm, bottom: 20mm
)

#let cibertec-heading-with-lines(
  heading_margin_before: 0mm, heading_line_spacing: 0mm, text_size: 14.3pt, text_weight: "bold", text_prefix: "",
  text_suffix: "", counter_suffix: "", it
) = {
  set text(font: "Roboto", fallback: false, weight: text_weight, size: text_size)
  block(breakable: false, inset: 0pt, outset: 0pt, fill: none)[
    #stack(
      v(heading_margin_before),
      line(length: 100%, stroke: cibertec_heading_line_thin_stroke),
      v(heading_line_spacing),
      block({
        text-prefix
        if it.outline and it.numbering != none {
          counter(heading).display(it.numbering)
          counter - suffix
          h(2pt)
        }
        it.body
        text - suffix
      }),
      v(heading_line_spacing),
      line(length: 100%, stroke: cibertec_heading_line_thin_stroke),
      v(10pt)
    )
  ]
}

#let cibertec-section-lines(above: 1.8em, below: 1.2em, ruled: true, body) = {
  block(width: 100%, inset: 0mm, outset: 0mm, above: above, below: below, {
    set block(spacing: 0.2em)
    
    if ruled {
      line(length: 100%, stroke: cibertec_body_line_height)
    }
    body
    
    if ruled {
      line(length: 100%, stroke: cibertec_body_line_height)
    }
  })
}

#let cibertec-section(title) = {
  cibertec-section-lines(text(title, font: "Roboto", weight: "bold", size: 11pt))
}

#let cibertec-subsection(title) = {
  cibertec-section-lines(above: 1.4em, below: 1em, text(title, font: "Roboto", weight: "regular", size: 11pt))
}

#let cibertec-subsection-unruled(title) = {
  cibertec-section-lines(above: 1.4em, below: 1em, ruled: false, text(title, font: "Roboto", weight: "regular", size: 11pt))
}