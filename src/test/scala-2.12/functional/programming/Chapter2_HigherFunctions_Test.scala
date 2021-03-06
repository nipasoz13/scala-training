package functional.programming

import book.functional.programming.Chapter2_HigherFunctions._
import book.functional.programming.datastructures
import book.functional.programming.datastructures.List.sum
import book.functional.programming.datastructures.{Cons, Nil}
import org.scalatest.{FunSpec, Matchers}

/**
  * Created by npa on 26/06/17.
  */
class Chapter2_HigherFunctions_Test extends FunSpec with Matchers {
  describe("Chapter 2") {
    describe("Exercise 2.1") {
      it("Should produce Fibonacci numbers") {
        fib(0) should be(0)
        fib(1) should be(1)
        fib(4) should be(3)
        fib(7) should be(13)
      }
    }

    describe("Exercise 2.2") {
      it("should return if a array is sorted according to a comparison") {
        val supOrEquals = (x: Int, y: Int) => x >= y
        isSorted(Array(5, 4, 3, 2, 1), supOrEquals) should be(true)
        isSorted(Array(5, 4, 10, 13), supOrEquals) should be(false)

        val infOrEquals = (x: String, y: String) => x <= y
        isSorted(Array("a", "b", "c", "d"), infOrEquals) should be(true)
        isSorted(Array("d", "a", "c", "d"), infOrEquals) should be(false)
      }
    }

    describe("Exercise 2.3") {
      it("should curry a function") {
        val multiply = (x: Int, y: Int) => x * y
        val curriedMultiply = curry(multiply)

        curriedMultiply(3)(4) should be(multiply(3, 4))
        curriedMultiply(5)(10) should be(multiply(5, 10))
      }
    }

    describe("Exercise 2.4:") {
      it("should uncurry a function") {
        val curriedMultiplied = (x: Int) => (y: Int) => x * y
        val uncurriedMultiply = uncurry(curriedMultiplied)

        uncurriedMultiply(3, 4) should be(curriedMultiplied(3)(4))
        uncurriedMultiply(5, 10) should be(curriedMultiplied(5)(10))
      }
    }

    describe("Exercise 2.5:") {
      it("should compose two functions") {
        val format = (x:Int) => "Formatted result %d".format(x)
        val absolute = (x:Int) => math.abs(x)

        compose(format, absolute)(-3) should be ("Formatted result 3")
      }
    }
  }
}
