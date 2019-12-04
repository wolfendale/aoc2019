package wolfendale

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class PointSpec extends AnyFreeSpec with Matchers {

  "Point" - {

    "manhattanDistance" - {

      "must return 0 when the points are the same" in {
        Point.origin.manhattanDistance(Point.origin) mustEqual 0
      }

      "must return the horizontal distance from the other point when vertically aligned" in {
        Point.origin.manhattanDistance(Point(10, 0)) mustEqual 10
      }

      "must return the vertical distance from the other point when horizontally aligned" in {
        Point.origin.manhattanDistance(Point(0, 10)) mustEqual 10
      }

      "must retun the sum of the vertical and horizontal distance from the other point" in {
        Point.origin.manhattanDistance(Point(10, 10)) mustEqual 20
      }
    }
  }
}
