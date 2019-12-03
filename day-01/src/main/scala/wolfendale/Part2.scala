package wolfendale

import scala.io.Source

object Part2 extends App {

  val masses = Source.fromResource("input")
    .getLines
    .map(_.toInt)

  val result = masses.foldLeft(0) {
    (m, n) => m + FuelCounterUpper.calculateTotalFuel(n)
  }

  println(result)
}
