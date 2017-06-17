package book.impatient

import book.impatient.Chapter5_Class.Counter
import org.scalatest.{FunSpec, Matchers}

/**
  * Created by npa on 17/06/17.
  */
class Chapter5Test extends FunSpec with Matchers {
  describe("Chapter 5: Classes") {
    describe("Exercise 1:") {
      it("should increase counter value on increment call") {
        val counterInitTo0 = new Counter()
        counterInitTo0.increment()
        counterInitTo0.current should be(1)

        counterInitTo0.increment()
        counterInitTo0.current should be(2)
      }

      it("counter value should go back to zero when it is incremented with a value of Int.MaxValue") {
        val counterInitToIntMax = new Counter(Int.MaxValue)
        counterInitToIntMax.increment()
        counterInitToIntMax.current should be(0)
      }
    }
  }
}
