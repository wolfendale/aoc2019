package wolfendale

import scala.io.Source

object Part2 extends App {

  private val program = Source.fromResource("input")
    .mkString
    .split(",")
    .toVector
    .map(_.toInt)

  private val computer = IntcodeComputer(program)

  def gravityAssist(noun: Int, verb: Int): Int =
    computer
      .set(1, noun)
      .set(2, verb)
      .run
      .get(0)
      .get

  val result = for {
    noun   <- 0 to 99
    verb   <- 0 to 99
    result =  gravityAssist(noun, verb)
    if result == 19690720
  } yield f"$noun%02d$verb%02d"

  println(result.head)
}
