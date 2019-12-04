package wolfendale

import scala.io.Source

object Part1 extends App {

  val wires = Source.fromResource("input").getLines.toList
  val one = Wire(wires(0))
  val two = Wire(wires(1))
  val intersection = one.closestIntersectionToOrigin(two).get
  val distanceFromOrigin = intersection.manhattanDistance(Point.origin)

  println(distanceFromOrigin)
}
