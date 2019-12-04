package wolfendale

import org.scalatest.OptionValues
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class WireSpec extends AnyFreeSpec with Matchers with OptionValues {

  "Wire" - {

    "closestIntersectionToOrigin" - {

      "must return 6 for test case 1" in {
        val one = Wire("R8,U5,L5,D3")
        val two = Wire("U7,R6,D4,L4")
        val closestIntersection = one.closestIntersectionToOrigin(two).value
        val result = closestIntersection.manhattanDistance(Point.origin)
        result mustEqual 6
      }

      "must return 159 for test case 2" in {
        val one = Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val two = Wire("U62,R66,U55,R34,D71,R55,D58,R83")
        val closestIntersection = one.closestIntersectionToOrigin(two).value
        val result = closestIntersection.manhattanDistance(Point.origin)
        result mustEqual 159
      }

      "must return 135 for test case 3" in {
        val one = Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val two = Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
        val closestIntersection = one.closestIntersectionToOrigin(two).value
        val result = closestIntersection.manhattanDistance(Point.origin)
        result mustEqual 135
      }
    }

    "firstIntersectionByDistance" - {

      "must return 30 for test case 1" in {
        val one = Wire("R8,U5,L5,D3")
        val two = Wire("U7,R6,D4,L4")
        val result = one.firstIntersectionByDistance(two).value
        result mustEqual 30
      }
    }
  }
}
