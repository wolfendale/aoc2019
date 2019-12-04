package wolfendale

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class PasswordAnalyzerSpec extends AnyFreeSpec with Matchers {

  "PasswordAnalyzer" - {

    "validate" - {

      "must return true for a valid password" in {
        PasswordAnalyzer("111111").validate mustEqual true
      }

      "must return false if the password contains a decreasing pair of digits" in {
        PasswordAnalyzer("223450").validate mustEqual false
      }

      "must return false if the password does not contain a double digit" in {
        PasswordAnalyzer("223450").validate mustEqual false
      }
    }
  }
}
