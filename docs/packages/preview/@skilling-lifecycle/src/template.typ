#import "core/colors.typ": cibertec_colors, cibertec_text_colors
#import "core/format.typ": cibertec_exercise_page_margin, cibertec_header_line_height, cibertec_inner_page_margin_top, cibertec_title_logo_height
#import "core/format.typ": cibertec-section, cibertec-subsection, cibertec-subsection-unruled
#import "core/util.typ": check-font-exists
#import "core/functions.typ": calc-relative-luminance, calc-contrast, overwrite-dict
#import "title.typ": *
#import "locales.typ": *

#let design_defaults = (
  accentcolor: "0b",
  colorback: true,
  darkmode: false
)

#let cibertec-lifecycle(
  language: "esp",
  margins: cibertec_exercise_page_margin,
  headline: ("title", "name", "id"),
  paper: "a4",
  logo: none,
  info: (
    title: none,
    header_title: none,
    subtitle: none,
    author: none,
    term: none,
    date: none,
    sheetnumber: none,
  ),
  design: design_defaults,
  show-title: true,
  subtask: "ruled",
  body
) = {
  if paper != "a4" {
    panic("Solo formato A4 es soportado")
  }
  let margins = overwrite-dict(margins, cibertec_exercise_page_margin)
  let design = overwrite-dict(design, design_defaults)
  let info = overwrite-dict(info, (
    title: none,
    header_title: none,
    subtitle: none,
    author: none,
    term: none,
    date: none,
    sheetnumber: none,
  ))

  let text_color = if design.darkmode {
    white
  } else {
    black
  }

  let background_color = if design.darkmode {
    rgb(29, 31, 33)
  } else {
    white
  }

  let accent_color = if type(design.accentcolor) == color {
    design.accentcolor
  } else if type(design.accentcolor) == str {
    rgb(cibertec_colors.at(design.accentcolor))
  } else {
    panic("No se soporta ese formato de color.")
  }

  let text_on_accent_color = if type(design.accentcolor) == str {
    cibertec_text_colors.at(design.accentcolor)
  } else {
    let lum = calc-relative-luminance(design.accentcolor) 
    if calc-contrast(lum, 0) > calc-contrast(lum, 1) {
      black
    } else {
      white
    }
  }

  state("cibertec_design").update((
    text_color: text_color,
    background_color: background_color,
    accent_color: accent_color,
    text_on_accent_color: text_on_accent_color
  ))

  set line(stroke: text_color)
  let ruled_subtask = if subtask == "ruled" {
    true
  } else if subtask == "plain" {
    false
  } else {
    panic("Solo 'ruled' y 'plain' tienen soporte")
  }

  let meta_document_title = if info.subtitle != none and info.title != none {
    [#info.subtitle #sym.dash.em #info.title]
  } else if info.title != none {
    info.title
  } else if info.subtitle != none {
    info.subtitle
  } else {
    none
  }

  set document(
    title: meta_document_title,
    author: if info.author != none {
      info.author
    } else {
      ()
    }
  )

  set par(
    justify: true,
    leading: 4.8pt,
    spacing: 1.1em
  )

  set text(
    font: "XCharter",
    size: 10.909pt,
    alternates: false,
    kerning: true,
    ligatures: false,
    spacing: 91%,
    fill: text_color
  )

  let dict = if language == "eng" {
    dict_en
  } else if language == "esp" {
    dict_es
  } else  {
    panic("Idioma no soportado")
  }

  set heading(numbering: (..numbers) => {
    if info.sheetnumber != none {
      numbering("1.1a", info.sheetnumber, ..numbers)
    } else {
      numbering("1a", ..numbers)
    }
  })

  show heading: it => {
    if not it.outlined or it.numbering == none {
      it
      return
    }
    let c = counter(heading).display(it.numbering)
    if it.level == 1 {
      cibertec-section(dict.task + " " + c + ": " + it.body)
    } else if it.level == 2 {
      if ruled_subtask {
        cibertec-subsection(c + ") " + it.body)
      } else {
        cibertec-subsection-unruled(c + ") " + it.body)
      }
    } else {
      it
    }
  }

  let identbar = rect(
    fill: accent_color,
    width: 100%,
    height: 4mm
  )

  let header_frontpage = grid(
    rows: auto,
    row-gutter: 1.4mm + 0.25mm,
    identbar,
    line(length: 100%, stroke: cibertec_header_line_height)
  )

  context {
    let height_header = measure(header_frontpage).height

    set page(
      paper: paper,
      numbering: "1",
      number-align: right,
      margin: (
        top: margins.top + cibertec_inner_page_margin_top + height_header,
        bottom: margins.bottom,
        left: margins.left,
        right: margins.right
      ),
      header: header_frontpage,
      header-ascent: cibertec_inner_page_margin_top,
      fill: background_color
    )

    if show-title {
      cibertec-make-title(
        cibertec_inner_page_margin_top,
        cibertec_header_line_height,
        accent_color,
        text_on_accent_color,
        text_color,
        design.colorback,
        logo,
        cibertec_title_logo_height,
        info,
        dict
      )
    }

    check-font-exists("Roboto")
    check-font-exists("XCharter")
    body
  }

}