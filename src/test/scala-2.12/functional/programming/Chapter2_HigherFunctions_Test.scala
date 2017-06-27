package functional.programming

import book.functional.programming.Chapter2_HigherFunctions._
import org.scalatest.{FunSpec, Matchers}
/**
  * Created by npa on 26/06/17.
  */
class Chapter2_HigherFunctions_Test extends FunSpec with Matchers {
  describe("Chapter 2") {
    describe("Exercise 1: Write loops recursively") {
      it("Should produce Fibonacci numbers") {
        fib(0) should be(0)
        fib(1) should be(1)
        fib(4) should be(3)
        fib(7) should be(13)
      }
    }
  }
}
