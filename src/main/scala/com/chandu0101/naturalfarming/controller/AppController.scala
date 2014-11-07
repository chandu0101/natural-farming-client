package com.chandu0101.naturalfarming.controller

import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope

import scala.scalajs.js.annotation.JSExport

/**
 * Created by chandrasekharkode on 11/7/14.
 *
 * Controller used to handle theme and layout
 */
@JSExport
object AppController extends Controller {

  override val name = "appctrl"
  override type ScopeType = AppScope

  override def initialize(scope: AppScope) = {
    scope._isMenuOpen = false
  }

  @JSExport
  def getBodyClass() =
    if (scope._isMenuOpen) "open" else ""

  @JSExport
  def getNavDrawerContainerClass() =
    if (scope._isMenuOpen) "open opened" else ""

  @JSExport
  def closeMenu() = scope._isMenuOpen = false;


  @JSExport
  def toggleMenu() = scope._isMenuOpen = true;


  trait AppScope extends Scope {
    var _isMenuOpen: Boolean
  }

}
