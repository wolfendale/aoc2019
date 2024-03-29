import sbt.Keys.version

lazy val commonSettings = Seq(
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full),
  scalacOptions += "-Ypartial-unification",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "2.0.0",
    "org.scalactic" %% "scalactic" % "3.1.0",
    "org.scalatest" %% "scalatest" % "3.1.0" % "test"
  )
)

lazy val root = (project in file("."))
  .settings(
    name := "aoc2019",
    version := "0.1",
    scalaVersion := "2.13.1",
  )

lazy val dayOne = (project in file("day-01"))
  .settings(
    name := "day one",
    commonSettings
  )

lazy val dayTwo = (project in file("day-02"))
  .settings(
    name := "day two",
    commonSettings
  )

lazy val dayThree = (project in file("day-03"))
  .settings(
    name := "day three",
    commonSettings
  )

lazy val dayFour = (project in file("day-04"))
  .settings(
    name := "day four",
    commonSettings
  )