package com.chandu0101.naturalforming

import com.chandu0101.naturalforming.controller.AppController
import com.chandu0101.naturalforming.routers.AppRouter
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
     val module = Angular.module("natural-farming",Seq("ngRoute"))
     module.controller(AppController)
     module.config(AppRouter)
   }
 }