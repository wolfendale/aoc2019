package wolfendale

object FuelCounterUpper {

  def calculateFuel(mass: Int): Int =
    mass / 3 - 2

  def calculateTotalFuel(mass: Int): Int = {
    lazy val stream: Stream[Int] = calculateFuel(mass) #:: stream.map(calculateFuel)
    stream.takeWhile(_ > 0).sum
  }
}
