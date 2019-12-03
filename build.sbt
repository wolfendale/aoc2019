import sbt.Keys.version

lazy val commonSettings = Seq(
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