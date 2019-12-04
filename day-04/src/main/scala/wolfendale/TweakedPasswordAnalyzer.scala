package wolfendale

import scala.annotation.tailrec
import scala.collection.immutable


final case class TweakedPasswordAnalyzer(
                                          input: String,
                                          pointer: Int = 0,
                                          private val _largestNumber: Int = 0,
                                          private val _decreasing: Boolean = false,
                                          private val _identicalAdjacentDigitFrequency: Map[Int, Int] = immutable.HashMap.empty
                                        ) {

  private val previous: Option[Int] =
    input.lift(pointer - 1).map(_.asDigit)

  private val current: Option[Int] =
    input.lift(pointer).map(_.asDigit)

  private val finished: Boolean =
    pointer > 5

  private val largestNumber: Int =
    current
      .map(_largestNumber.max)
      .getOrElse(_largestNumber)

  private val decreasing: Boolean =
    (for (a <- previous; b <- current) yield a > b)
      .map(_ || _decreasing)
      .getOrElse(_decreasing)

  private val identicalAdjacentDigitFrequency: Map[Int, Int] = {
    for {
      previous <- previous
      current  <- current
      if previous == current
    } yield {
      _identicalAdjacentDigitFrequency +
        (current -> _identicalAdjacentDigitFrequency.get(current).map(_ + 1).getOrElse(1))
    }
  }.getOrElse(_identicalAdjacentDigitFrequency)

  private lazy val hasDoubleDigit: Boolean =
    identicalAdjacentDigitFrequency.valuesIterator.contains(1)

  private def advance: TweakedPasswordAnalyzer =
    copy(
      pointer         = pointer + 1,
      _largestNumber  = largestNumber,
      _decreasing     = decreasing,
      _identicalAdjacentDigitFrequency = identicalAdjacentDigitFrequency
    )

  private val valid: Boolean =
    finished && !decreasing && hasDoubleDigit

  @tailrec
  private def run: Option[TweakedPasswordAnalyzer] =
    if (finished) Some(this) else advance.run

  lazy val validate: Boolean =
    run.exists(_.valid)
}
