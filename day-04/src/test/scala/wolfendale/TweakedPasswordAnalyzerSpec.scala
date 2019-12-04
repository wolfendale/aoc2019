package wolfendale

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class TweakedPasswordAnalyzerSpec extends AnyFreeSpec with Matchers {

  "TweakedPasswordAnalyzerSpec" - {

    "validate" - {

      "must return true for a valid password" in {
        TweakedPasswordAnalyzer("112233").validate mustEqual true
      }

      "must return false for a password where the only repeated digits are too frequent" in {
        TweakedPasswordAnalyzer("123444").validate mustEqual false
      }

      "must return true for a valid password with overly frequent extra digits" in {
        TweakedPasswordAnalyzer("111122").validate mustEqual true
      }
    }
  }
}
