package com.chandu0101.naturalfarming.components

import japgolly.scalajs.react.{SyntheticEvent, BackendScope, ReactComponentB}
import japgolly.scalajs.react.vdom.ReactVDom.ReactAttrExt
import japgolly.scalajs.react.vdom.ReactVDom.all._
import org.scalajs.dom.{HTMLAnchorElement, Node}

/**
 * Created by chandrasekharkode on 11/10/14.
 */
object Pager {

  val Pager = ReactComponentB[(Int, Int,Int,SyntheticEvent[HTMLAnchorElement] => Unit,SyntheticEvent[HTMLAnchorElement] => Unit)]("Pager")
    .render(P => {
    val itemsPerPage = P._1
    val totalItems = P._2
    val offset = P._3
    val handleNext = P._4
    val handlePrevious = P._5
    var cprevious = "hide"
    var cnext = "hide"
    if (!(totalItems <= itemsPerPage)) {
      if (offset > 0 && totalItems > offset + itemsPerPage){ cprevious ="previous" ; cnext = "next" }
      else if (offset > 0) cprevious = "previous"
      else cnext = "next"
    }
    ul(`class` := "pager")(
      li(`class` := cprevious)(a(onclick ==>  handlePrevious)("← Previous")),
      li(`class` := cnext)(a(onclick ==> handleNext)("Next →"))
    )
  }).build

  def component(itemsPerPage : Int, totalItems : Int, offset : Int, nextClick : SyntheticEvent[HTMLAnchorElement] => Unit , previousClick : SyntheticEvent[HTMLAnchorElement] => Unit) = {
    Pager((itemsPerPage,totalItems,offset,nextClick,previousClick))
  }

}
