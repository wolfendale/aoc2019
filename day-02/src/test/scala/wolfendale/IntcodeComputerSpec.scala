package wolfendale

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class IntcodeComputerSpec extends AnyFreeSpec with Matchers {

  "IntcodeComputer" - {

    "run" - {

      "must return the correct state for test case 1" in {
        val program = Vector(1,9,10,3,2,3,11,0,99,30,40,50)
        val result = IntcodeComputer(program).run.state
        result mustEqual Vector(3500,9,10,70,2,3,11,0,99,30,40,50)
      }

      "must return the correct state for test case 2" in {
        val program = Vector(1,0,0,0,99)
        val result = IntcodeComputer(program).run.state
        result mustEqual Vector(2,0,0,0,99)
      }

      "must return the correct state for test case 3" in {
        val program = Vector(2,3,0,3,99)
        val result = IntcodeComputer(program).run.state
        result mustEqual Vector(2,3,0,6,99)
      }

      "must return the correct state for test case 4" in {
        val program = Vector(2,4,4,5,99,0)
        val result = IntcodeComputer(program).run.state
        result mustEqual Vector(2,4,4,5,99,9801)
      }

      "must return the correct state for test case 5" in {
        val program = Vector(1,1,1,4,99,5,6,0,99)
        val result = IntcodeComputer(program).run.state
        result mustEqual Vector(30,1,1,4,2,5,6,0,99)
      }
    }
  }
}
