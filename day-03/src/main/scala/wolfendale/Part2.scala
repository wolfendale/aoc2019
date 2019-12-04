package wolfendale

import scala.io.Source

object Part2 extends App {

  val wires = Source.fromResource("input").getLines.toList
  val one = Wire(wires(0))
  val two = Wire(wires(1))
  val result = one.firstIntersectionByDistance(two)

  println(result)
}
