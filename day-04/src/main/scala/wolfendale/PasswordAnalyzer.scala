package wolfendale

import scala.annotation.tailrec


final case class PasswordAnalyzer(
                             input: String,
                             pointer: Int = 0,
                             private val _largestNumber: Int = 0,
                             private val _hasDoubleDigit: Boolean = false,
                             private val _decreasing: Boolean = false
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

  private val hasDoubleDigit: Boolean =
    (for (a <- previous; b <- current) yield a == b)
      .map(_ || _hasDoubleDigit)
      .getOrElse(_hasDoubleDigit)

  private val decreasing: Boolean =
    (for (a <- previous; b <- current) yield a > b)
      .map(_ || _decreasing)
      .getOrElse(_decreasing)

  private def advance: PasswordAnalyzer =
    copy(
      pointer         = pointer + 1,
      _largestNumber  = largestNumber,
      _hasDoubleDigit = hasDoubleDigit,
      _decreasing     = decreasing
    )

  private val valid: Boolean =
    finished && hasDoubleDigit && !decreasing

  @tailrec
  private def run: Option[PasswordAnalyzer] =
    if (finished) Some(this) else advance.run

  lazy val validate: Boolean =
    run.exists(_.valid)
}
