package wolfendale

import scala.io.Source

object Part1 extends App {

  val program = Source.fromResource("input")
    .mkString
    .split(",")
    .toVector
    .map(_.toInt)

  val result = IntcodeComputer(program)
    .set(1, 12)
    .set(2, 2)
    .run
    .get(0)
    .get

  println(result)
}
