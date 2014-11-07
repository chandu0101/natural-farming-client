
import scala.scalajs.sbtplugin.ScalaJSPlugin.ScalaJSKeys._

name := "natural-farming"

version := "1.0"

scalaVersion := "2.11.2"

scalaJSSettings

persistLauncher := true

persistLauncher in Test := false


libraryDependencies ++= Seq("com.greencatsoft" %%% "scalajs-angular" % "0.2",
  "com.github.japgolly.scalajs-react" %%% "core" % "0.5.1")

// React itself
//   (react-with-addons.js can be react.js, react.min.js, react-with-addons.min.js)
jsDependencies ++= Seq("org.webjars" % "angularjs" % "1.3.0" / "angular.min.js",
  "org.webjars" % "angularjs" % "1.3.0" / "angular-route.min.js" dependsOn  "angular.min.js",
  "org.webjars" % "react" % "0.11.1" / "react-with-addons.js" commonJSName "React"
  )
//  )

// creates single js resource file for easy integration in html page
skip in packageJSDependencies := false