package book.impatient

import org.scalatest.{FunSpec, Matchers}
import Chapter3_Arrays._
import scala.collection.mutable.ArrayBuffer

/**
  * Created by npa on 23/04/17.
  */
class Chapter3Test extends FunSpec with Matchers {
  describe("Chapter 3: Working with Arrays") {
    describe("Exercise 1") {
      it("Should set an array with random integers between 0 inclusive and n exclusive") {
        val a = new Array[Int](10);
        setArrayWithRandomLambda(a, 10)
        all(a) should (be >= 0 and be < 10)
        a should have size (10)

        val b = new Array[Int](10);
        setArrayWithRandomForLoop(b, 10)
        all(b) should (be >= 0 and be < 10)
        b should have size (10)
      }
    }

    describe("Exercise 2") {
      it("Should swap adjacent elements of an array") {
        val a = Array(1, 2, 3, 4, 5);
        swap(a);
        a should be(Array(2, 1, 4, 3, 5))
      }
    }

    describe("Exercise 3") {
      it("Should return a new array with swapped elements") {
        val a = Array(1, 2, 3, 4, 5);
        val swappedArray = swap2(a);
        swappedArray should be(Array(2, 1, 4, 3, 5))
      }
    }

    describe("Exercise 4") {
      it("Should produces a new array order by parity") {
        val a = Array(-1, 0, 2, -6, -40, 40)
        val orderedArray = orderByParity(a)
        orderedArray should be(Array(2, 40, -1, 0, -6, -40))
      }
    }

    describe("Exercise 5") {
      it("Should compute the average") {
        val a = Array(10.2, 20.4, 30)
        average(a) should be(20.2)
      }
    }

    describe("Exercise 6") {
      it("should reverse an array") {
        val a = Array(15, 22, 13, 4, 5)
        reverse(a)
        a should be(Array(5, 4, 13, 22, 15))
      }

      it("should reverse an array buffer") {
        val a = ArrayBuffer(15, 22, 13, 4, 5)
        reverse(a)
        a should be(ArrayBuffer(5, 4, 13, 22, 15))
      }
    }

    describe("Exercise 7") {
      it("should remove duplicates") {
        val a = Array(1, 2, 3, 1)
        val b = removeDuplicates(a)
        b should be(Array(1, 2, 3))
      }
    }

    describe("Exercise 8: remove negative numbers except the first one") {
      it("should remove elements if array has more than two negative numbers") {
        val a = ArrayBuffer(1, -2, -3, 4, -5)
        removeAllNegativesExceptFirst(a)
        a should be(ArrayBuffer(1, -2, 4))
      }

      it("should not modify an array with no negative numbers") {
        val a = ArrayBuffer(1, 2, 3, 4, 5)
        removeAllNegativesExceptFirst(a)
        a should be(a)
      }

      it("should not modify an array with only one negative number") {
        val a = ArrayBuffer(1, 2, -3, 4, 5)
        removeAllNegativesExceptFirst(a)
        a should be(a)
      }
    }
  }

  describe("Exercise 9: remove negative numbers except the first one, improved") {
    it("should remove negative numbers except the first one") {
      val a = ArrayBuffer(1, -2, 3, -4, 5, -6, 7)
      removeAllNegativesExceptFirstImproved(a)
      a should be(ArrayBuffer(1, -2, 3, 5, 7))
    }

    it("should not modify an array with no negative numbers") {
      val a = ArrayBuffer(1, 2, 3, 4, 5)
      removeAllNegativesExceptFirstImproved(a)
      a should be(a)
    }

    it("should not modify an array with only one negative number") {
      val a = ArrayBuffer(1, 2, -3, 4, 5)
      removeAllNegativesExceptFirstImproved(a)
      a should be(a)
    }
  }

  describe("Exercise 10: Get americans time zones") {
    it("should returns 165 time zones") {
      val timeZones = getAmericanTimeZones()
      timeZones should have length(165)
    }

    it("should returns time zones without America/ prefix") {
      val timeZones = getAmericanTimeZones()
      no(timeZones) should startWith("America/")
    }

    it("should returns sorted time zones") {
      val timeZones = getAmericanTimeZones()
      timeZones shouldBe sorted
    }
  }

}

