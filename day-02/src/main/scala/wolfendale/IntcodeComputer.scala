package wolfendale

import cats.implicits._

import scala.annotation.tailrec

final case class IntcodeComputer(state: Vector[Int], pointer: Int = 0) {

  def get(address: Int): Option[Int] =
    state.lift(address)

  def set(address: Int, value: Int): IntcodeComputer =
    copy(state = state.updated(address, value))

  private object Add {

    def unapply(c: IntcodeComputer): Option[(Int, Int, Int)] =
      c.opcode.flatMap {
        case 1 =>
          (c.getAtOffset(1).flatMap(c.get), c.getAtOffset(2).flatMap(c.get), c.getAtOffset(3))
            .tupled
        case _ => None
      }
  }

  private object Multiply {

    def unapply(c: IntcodeComputer): Option[(Int, Int, Int)] =
      c.opcode.flatMap {
        case 2 =>
          (c.getAtOffset(1).flatMap(c.get), c.getAtOffset(2).flatMap(c.get), c.getAtOffset(3))
            .tupled
        case _ =>
          None
      }
  }

  private object Exit {

    def unapply(c: IntcodeComputer): Option[Unit] =
      c.opcode.flatMap {
        case 99 => Some(())
        case _  => None
      }
  }

  private def opcode: Option[Int] =
    state.lift(pointer)

  private def getAtOffset(offset: Int): Option[Int] =
    state.lift(pointer + offset)

  private def advance(i: Int): IntcodeComputer =
    copy(pointer = pointer + i)

  @tailrec
  def run: IntcodeComputer =
    this match {
      case Add(a, b, target)      => set(target, a + b).advance(4).run
      case Multiply(a, b, target) => set(target, a * b).advance(4).run
      case Exit(())               => this
    }
}
