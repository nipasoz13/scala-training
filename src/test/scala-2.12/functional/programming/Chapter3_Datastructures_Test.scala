package functional.programming

import book.functional.programming.Chapter3_Datastructures._
import book.functional.programming.datastructures
import book.functional.programming.datastructures.List._
import book.functional.programming.datastructures._
import org.scalatest.{FunSpec, Matchers}

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
      it("should return a list the replaced head") {
        setHead(10, List(1, 2, 3, 4)) should be(List(10, 2, 3, 4))
        setHead(10, Nil) should be(List(10))
      }
    }

    describe("Exercice 3.4: drop n elements of a list") {
      it("should return a list without its n first element") {
        drop(List(1, 2, 3, 4), 2) should be(List(3, 4))
        drop(Nil, 10) should be(Nil)
      }
    }

    describe("Exercice 3.5: drop n elements of a list") {
      it("should return a list without its first element satisfying a condition") {
        dropWhile(List(1, 2, 10, 3, 4), (x: Int) => x < 10) should be(List(10, 3, 4))
        dropWhile(Nil, (x: Int) => x < 10) should be(Nil)
      }
    }

    describe("Exercice 3.6: drop the last element of a list") {
      it("should return a list without its last element") {
        init(List(1, 2, 3, 4)) should be(List(1, 2, 3))
        init(Nil) should be(Nil)
      }
    }

    describe("Exercise 3.9: Compute the length of a list using foldRight") {
      it("should compute the length") {
        List.length(Nil) should be(0)
        List.length(List(4, 5, 6)) should be(3)
      }
    }

    describe("Exercise 3.10: Foldleft tail recursive") {
      it("should fold") {
        foldLeft(List(1, 2, 3, 4), "")(_ + _) should be("1234")
      }
    }

    describe("Exercise 3.11: Write sum, product, and a function to compute the " +
      "length of a list using foldLeft.") {
      it("should sum suing foldleft") {
        sumFl(List()) should be(0)
        sumFl(List(-1, 5, 0, 4)) should be(8)
      }

      it("should multiply using foldleft") {
        productFl(List()) should be(1)
        productFl(List(-0.5, 4.0)) should be(-2d)
      }

      it("should get length using foldleft") {
        lengthFl(List()) should be(0)
        lengthFl(List(1, 2, 5)) should be(3)
      }
    }

    describe("Exercise 3.12: Reverse using a fold") {
      it("should reverse an empty list") {
        reverseFl(List()) should be(List())
      }

      it("should reverse a non empty list") {
        reverseFl(List(1, 2, 3)) should be(List(3, 2, 1))
      }
    }
  }
}
