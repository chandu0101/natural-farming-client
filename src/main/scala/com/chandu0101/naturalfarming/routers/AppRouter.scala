package com.chandu0101.naturalfarming.routers

import com.greencatsoft.angularjs.core.{Route, RouteProvider}
import com.greencatsoft.angularjs.{inject, Config}

/**
 * Created by chandrasekharkode on 11/7/14.
 */
object AppRouter extends Config {

  @inject
  var routeProvider: RouteProvider = _

  override def initialize() {
    routeProvider
      .when("/", Route("pages/home.html", "Home"))
      .when("/seeds", Route("pages/seeds.html", "Seeds"))
      .when("/about", Route("pages/about.html", "About"))
  }

}
