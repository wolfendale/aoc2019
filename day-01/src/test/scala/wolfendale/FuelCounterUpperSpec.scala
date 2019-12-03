package wolfendale

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers


class FuelCounterUpperSpec extends AnyFreeSpec with Matchers {

  "FuelCounterUpper" - {

    "calculateFuel" - {

      "must return 2 for a mass of 12" in {
        FuelCounterUpper.calculateFuel(12) mustEqual 2
      }

      "must return 2 for a mass of 14" in {
        FuelCounterUpper.calculateFuel(14) mustEqual 2
      }

      "must return 654 for a mass of 1969" in {
        FuelCounterUpper.calculateFuel(1969) mustEqual 654
      }

      "must return a mass of 33583 for a mass of 100756" in {
        FuelCounterUpper.calculateFuel(100756)
      }
    }

    "calculateTotalFuel" - {

      "must return 2 for a mass of 14" in {
        FuelCounterUpper.calculateTotalFuel(14) mustEqual 2
      }

      "must return 966 for a mass of 1969" in {
        FuelCounterUpper.calculateTotalFuel(1969) mustEqual 966
      }

      "must return 50346 for a mass of 100756" in {
        FuelCounterUpper.calculateTotalFuel(100756) mustEqual 50346
      }
    }
  }
}
