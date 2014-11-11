package com.chandu0101.naturalfarming.components

import com.chandu0101.naturalfarming.model.TumblerPost
import japgolly.scalajs.react.vdom.ReactVDom.ReactAttrExt
import japgolly.scalajs.react.vdom.ReactVDom.all._
import japgolly.scalajs.react.{BackendScope, React, ReactComponentB, SyntheticEvent}
import org.scalajs.dom.{HTMLAnchorElement, HTMLInputElement, Node}

/**
 * Created by chandrasekharkode on 11/8/14.
 */
object BlogReader {

  case class State(postsPerPage : Int,offset: Int, filterText: String,filtered : List[TumblerPost])

  class Backend(t: BackendScope[List[TumblerPost], State]) {
    def onPreviousClick(e: SyntheticEvent[HTMLAnchorElement]) = {
      t.modState(s => s.copy(offset = s.offset - s.postsPerPage))
    }

    def onNextClick(e: SyntheticEvent[HTMLAnchorElement]) = {
      t.modState(s => s.copy(offset = s.offset + s.postsPerPage))
    }

    def onTextChange(e: SyntheticEvent[HTMLInputElement]) =
      t.modState(_.copy(offset = 0,filterText = e.target.value,filtered = filteredPosts(t.props,e.target.value)))
  }

  val Post = ReactComponentB[TumblerPost]("Post")
    .render(post => li(`class` := "list-group item")(
    div(`class` := "panel panel-default")(
      div(`class` := "panel-heading")(
        strong(post.title)
      ),
      div(`class` := "panel-body")(
        post.body
      )
    )
  )).build

  val SearchBaar = ReactComponentB[(String, Backend)]("SearchBar")
    .render(P => {
    val text = P._1
    val B = P._2
    div(`class` := "form-group")(
      input(`class` := "form-control", `type` := "text", placeholder := "Search..", onchange ==> B.onTextChange)
    )
  }).build


  val Posts = ReactComponentB[(State)]("Posts")
    .render(S => {
    val rows = S.filtered.slice(S.offset, S.offset + S.postsPerPage) map { p => Post.withKey(p.title)(p)}
    ul(`class` := "list-group")(rows)
  }).build


  def filteredPosts(posts:List[TumblerPost], filterText : String) =
   if(filterText == "") posts
   else posts.filter(p => (p.title.indexOf(filterText) != -1 || p.body.indexOf(filterText) != -1))

  def componet(data:List[TumblerPost],mountNode: Node,postPerPage:Int =5) = {

    val data2 = (1 to 20).toList.map(i => TumblerPost("title" + i, "body" + i))

    val TumblerApp = ReactComponentB[List[TumblerPost]]("TumblerApp")
      .initialState(State(postsPerPage = postPerPage, 0, "",data2))
      .backend(new Backend(_))
      .render((P, S, B) => {
      filteredPosts(posts = P,filterText = "")
      div(
        SearchBaar((S.filterText, B)),
        Posts((S)),
        Pager.component(S.postsPerPage,S.filtered.length,S.offset,B.onNextClick,B.onPreviousClick)
      )
    }).build


    React.renderComponent(TumblerApp(data2), mountNode)
  }

}
