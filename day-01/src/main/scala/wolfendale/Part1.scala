package wolfendale

import scala.io.Source

object Part1 extends App {

  val masses = Source.fromResource("input")
    .getLines
    .map(_.toInt)

  val result = masses.foldLeft(0) {
    (m, n) => m + FuelCounterUpper.calculateFuel(n)
  }

  println(result)
}
