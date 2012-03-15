// SBT Basic Configuration File

name := "Chat RC"

version := "0.1.0"

scalaVersion := "2.9.1"

// sbt-onejar config

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

mainClass in oneJar := Some("edu.spsu.rgoodwin.chatrc.ClientApp")

// sbt-dependency-graph config

seq(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)

// Extra Libraries

// Scala Swing

libraryDependencies ++= Seq(
	"org.scala-lang" % "scala-swing" % "2.9.1"
)

resolvers += ScalaToolsReleases

// Akka

libraryDependencies += "se.scalablesolutions.akka" % "akka-actor" % "1.2"

libraryDependencies += "se.scalablesolutions.akka" % "akka-remote" % "1.2"

libraryDependencies += "se.scalablesolutions.akka" % "akka-stm" % "1.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"