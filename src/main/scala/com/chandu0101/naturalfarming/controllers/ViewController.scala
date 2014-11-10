package com.chandu0101.naturalfarming.controllers

import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import com.chandu0101.naturalfarming.model.TumblerPost
import com.chandu0101.naturalfarming.services.TumblerService
import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{Controller, inject}

import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}

/**
 * Created by chandrasekharkode on 11/9/14.
 */
@JSExport
object ViewController extends Controller {

  override val name = "viewctrl"
  override type ScopeType = ViewScope

  @inject
  var tService: TumblerService = _

  override def initialize(scope: ScopeType) = {
    tService.getPosts() onComplete {
      case Success(posts) => println("sucess*************")
         scope.posts = posts
      case Failure(t) => println("errormessage " + t.getMessage)
    }
    scope.name = "dude"
  }


  trait ViewScope extends Scope {
    var name: String
    var posts: Seq[TumblerPost]
  }

}
