package com.chandu0101.naturalfarming

import com.chandu0101.naturalfarming.controllers.{ViewController, AppController}
import com.chandu0101.naturalfarming.routers.AppRouter
import com.chandu0101.naturalfarming.services.TumblerServiceFactory
import com.greencatsoft.angularjs.Angular

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

/**
 * Created by chandrasekharkode on 10/26/14.
 */

@JSExport
object App extends JSApp {
  @JSExport
  override def main() {
    val module = Angular.module("natural-farming", Seq("ngRoute"))
    module.controller(AppController)
    module.controller(ViewController)
    module.config(AppRouter)
    module.factory(TumblerServiceFactory)

  }
}