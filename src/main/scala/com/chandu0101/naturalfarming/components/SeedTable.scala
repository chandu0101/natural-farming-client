package com.chandu0101.naturalfarming.components

import japgolly.scalajs.react.vdom.ReactVDom.ReactAttrExt
import japgolly.scalajs.react.vdom.ReactVDom.all._
import japgolly.scalajs.react.{BackendScope, ReactComponentB, SyntheticEvent}
import org.scalajs.dom.{HTMLAnchorElement, HTMLInputElement, Node}

/**
 * Created by chandrasekharkode on 11/9/14.
 */
object SeedTable {

  case class Seed(name: String, category: String, duration: Int, location: String, info: String)

  case class State(filterText: String, offset: Int, rowsPerPage: Int, filtered: List[Seed])

  class Backend(t: BackendScope[List[Seed], State]) {

    def onTextChange(e: SyntheticEvent[HTMLInputElement]) =
      t.modState(_.copy(filterText = e.target.value, filtered = t.props.filter(predicate(_, e.target.value)),offset = 0))

    def onPreviousClick(e: SyntheticEvent[HTMLAnchorElement]) = {
      t.modState(s => s.copy(offset = s.offset - s.rowsPerPage))
    }

    def onNextClick(e: SyntheticEvent[HTMLAnchorElement]) = {
      t.modState(s => s.copy(offset = s.offset + s.rowsPerPage))
    }

  }

  val SeedRow = ReactComponentB[Seed]("SeedRow")
    .render(seed => {
    tr(
      td(seed.name),
      td(seed.category),
      td(seed.duration),
      td(seed.location),
      td(seed.info)
    )
  }).build

  val predicate = (seed: Seed, text: String) => seed.name.indexOf(text) != -1 || seed.category.indexOf(text) != -1

  val SeedTable = ReactComponentB[(State)]("SeedTable")
    .render(S => {
    val rows = S.filtered.slice(S.offset, S.offset + S.rowsPerPage).map(seed => SeedRow.withKey(seed.name)(seed))
    table(`class` := "table table-striped table-hover")(
      thead(
        tr(
          th("Name"),
          th("Category"),
          th("Duration"),
          th("Location"),
          th("Info")
        )
      ),
      tbody(
        rows
      )
    )
  }).build

  val SearchBar = ReactComponentB[(State, Backend)]("SearchBar")
    .render(P => {
    val S = P._1
    val B = P._2
    div(`class` := "form-group")(
      input(`class` := "form-control", `type` := "text", placeholder := "Search..", onchange ==> B.onTextChange)
    )
  }).build

  def component(data: List[Seed], mountNode: Node) = {
    val data2 = (1 to 20).toList map { i => Seed("name" + i, "categoty" + i, i, "loaction" + i, "info" + i)}
    val SeedSearch = ReactComponentB[List[Seed]]("SeedSearch")
      .initialState(State("", offset = 0, rowsPerPage = 5, data2))
      .backend(new Backend(_))
      .render((P, S, B) => {
      div(
        SearchBar((S, B)),
        SeedTable(S),
        Pager.component(S.rowsPerPage, S.filtered.length, S.offset, B.onNextClick, B.onPreviousClick)
      )
    }).build

    SeedSearch(data2) render mountNode
  }

}
