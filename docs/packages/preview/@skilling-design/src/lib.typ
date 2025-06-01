#import "core/copyright.typ": *
#import "core/frontpage.typ": *
#import "core/lastpage.typ": *
#import "core/preamble.typ": *

#let hide-formalities = false
#let skilling-design(
  title: "", authors: (), date: none, course: "", educational-center: "", department: "", department-full-title: "", address-i: "", address-ii: "", teacher: "", code: "", cicle: "", department-website: "", before: (), frontpage-input: none, background-color: rgb("#013656"), body
) = {
  // Frontpage
  if frontpage-input == none {
    show: front-page.with(
      title: title,
      authors: authors,
      date: date,
      course: course,
      department: department,
      teacher: teacher,
      code: code,
      cicle: cicle,
      department-full-title: department-full-title
    )
  } else {
    frontpage-input
  }

  // Setup
  set text(size: 10pt, fill: black)
  set document(author: authors, title: title)
  set page(numbering: none, number-align: center, fill: none, margin: auto)
  set par(leading: 0.65em)
  set heading(numbering: none)
  show heading: set block(above: 1.4em, below: 1em)

  // Formalities
  if not hide-formalities {
    show: copyright.with(
      title: title,
      authors: authors,
      date: date,
      course: course,
      educational-center: educational-center,
      department: department,
      department-full-title: department-full-title,
      address-i: address-i,
      address-ii: address-ii
    )
    set page(numbering: "i", number-align: center)
    counter(page).update(1)
    include-files(before)
    pagebreak()
  } else {
    set page(numbering: "i", number-align: center)
    counter(page).update(1)
    before.contents
  }

  // Main
  set page(numbering: "1", number-align: center)
  set heading(numbering: "1.1")
  counter(page).update(1)
  set par(justify: true)
  set text(hyphenate: false)

  // Custom Heading
  show heading.where(level: 1): it => custom-heading(it) 
  set page(footer: context {
    if calc.rem(here().page(), 2) == 0 [
      #text(current-h(level: 1))
      #h(1fr)
      #counter(page).display()
    ] else [
      #counter(page).display()
      #h(1fr)
      #text(current-h(level: 1))
    ]
  })
  body

  show: last-page.with(
    department: department,
    educational-center: educational-center,
    address-i: address-i,
    address-ii: address-ii,
    department-website: department-website,
    background-color: background-color
  )
}