package functional.programming

import book.functional.programming.datastructures
import org.scalatest.{FunSpec, Matchers}
import book.functional.programming.datastructures.List._
import book.functional.programming.datastructures._

/**
  * Created by npa on 8/07/17.
  */
class Chapter3_Datastructures_Test extends FunSpec with Matchers {
  describe("Chapter 3: Datastructures") {
    it("Exercice 3.3: Pattern matching") {
      val x = datastructures.List(1, 2, 3, 4, 5) match {
        case Cons(x, Cons(2, Cons(4, _))) => x
        case Nil => 42
        case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
        case Cons(h, t) => h + sum(t)
        case _ => 101
      }
      x should be(3)
    }

    describe("Exercice 3.2: Tail") {
      it("should return a list without its first element") {
        tail(List(1, 2, 3, 4)) should be(List(2, 3, 4))
        tail(Nil) should be(Nil)
      }
    }

    describe("Exercice 3.3: set Head") {
      it("should return a list without its first element") {
        setHead(10, List(1, 2, 3, 4)) should be(List(10, 2, 3, 4))
        setHead(10, Nil) should be(List(10))
      }
    }
  }
}
