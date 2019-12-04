package wolfendale

import cats._
import cats.data._
import cats.implicits._

final case class Point(x: Int, y: Int) {

  def to(that: Point): Chain[Point] = {

    def direction(a: Int, b: Int): Int =
      if (a <= b) 1 else -1

    val horizontal = x.to(that.x, direction(x, that.x)).tail.map(Point(_, y))
    val vertical = y.to(that.y, direction(y, that.y)).tail.map(Point(that.x, _))

    Chain.fromSeq(horizontal ++ vertical)
  }

  def +(that: Point): Point =
    Point(x + that.x, y + that.y)

  def -(that: Point): Point =
    Point(x - that.x, y - that.y)

  def manhattanDistance(that: Point): Int = {
    val vector = (this - that)
    vector.x.abs + vector.y.abs
  }
}


object Point {

  val origin: Point = Point(0, 0)
}

final case class Wire(instructions: Chain[Wire.Instruction]) {

  lazy val points: Chain[Point] =
    instructions.sequence.runA(Point.origin).value.flatten

  def intersect(that: Wire): Set[Point] = {
    points.iterator.toSet intersect that.points.iterator.toSet
  }

  def closestIntersectionToOrigin(that: Wire): Option[Point] =
    intersect(that).minimumOption(Order.by(_.manhattanDistance(Point.origin)))

  def firstIntersectionByDistance(that: Wire): Option[Int] = {
    intersect(that)
      .toList
      .flatMap {
        p =>
          (this.stepsToPoint(p), that.stepsToPoint(p))
            .mapN(_ + _)
      }
      .sorted
      .headOption
  }

  def stepsToPoint(point: Point): Option[Int] =
    points.zipWithIndex.find(_._1 == point).map(_._2 + 1)
}

object Wire {

  def apply(instructions: String): Wire = {
    val i = instructions
      .split(",")
      .map(s => Instruction.fromString(s.trim))
    Wire(Chain.fromSeq(i))
  }

  type Instruction = State[Point, Chain[Point]]

  object Instruction {

    def fromString(string: String): Instruction =
      string match {
        case Up(i)    => i
        case Right(i) => i
        case Down(i)  => i
        case Left(i)  => i
      }

    private def moveAlongVector(vector: Point): Instruction =
      State { currentPosition =>
        val destination = currentPosition + vector
        (destination, currentPosition to destination)
      }

    object Up {

      private val Pattern = "^U(\\d+)$".r
      def unapply(string: String): Option[Instruction] = string match {
        case Pattern(distance) => Some(moveAlongVector(Point(0, distance.toInt)))
        case _                 => None
      }
    }

    object Right {
      private val Pattern = "^R(\\d+)$".r
      def unapply(string: String): Option[Instruction] = string match {
        case Pattern(distance) => Some(moveAlongVector(Point(distance.toInt, 0)))
        case _                 => None
      }
    }

    object Down {
      private val Pattern = "^D(\\d+)$".r
      def unapply(string: String): Option[Instruction] = string match {
        case Pattern(distance) => Some(moveAlongVector(Point(0, -distance.toInt)))
        case _                 => None
      }
    }

    object Left {
      private val Pattern = "^L(\\d+)$".r
      def unapply(string: String): Option[Instruction] = string match {
        case Pattern(distance) => Some(moveAlongVector(Point(-distance.toInt, 0)))
      }
    }
  }
}
