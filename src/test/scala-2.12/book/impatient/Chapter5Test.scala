package book.impatient

import book.impatient.Chapter5_Class.{BankAccount, Counter, Time, Time2}
import org.scalatest.{FunSpec, Matchers}

/**
  * Created by npa on 17/06/17.
  */
class Chapter5Test extends FunSpec with Matchers {
  describe("Chapter 5: Classes") {
    describe("Exercise 1: Counter") {
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

    describe("Exercise 2: Bank account") {
      it("balance should display current value account") {
        val newAccount = new BankAccount
        newAccount.balance should be(0)
      }

      it("should decrease balance on withdrawal") {
        val account = new BankAccount
        account.withdraw(50)
        account.balance should be(-50)
      }

      it("should increase value on deposit") {
        val account = new BankAccount
        account.deposit(100)
        account.balance should be(100)
      }
    }

    describe("Exercise 3: Time") {
      it("should throw an error if time is constructed with an hour outside the 0-23 range") {
        intercept[IllegalArgumentException] {
          new Time(24, 1)
        }
        intercept[IllegalArgumentException] {
          new Time(-1, 3)
        }
      }
      it("should throw an error if time is constructed with minutes outside 0-59 range") {
        intercept[IllegalArgumentException] {
          new Time(12, -2)
        }
        intercept[IllegalArgumentException] {
          new Time(11, 60)
        }
      }
      it("should be defined hours and minutes") {
        val time = new Time(10, 23)
        time.hours should be(10)
        time.minutes should be(23)
      }
      it("before should compare dates in chronological order") {
        new Time(10, 2) before new Time(10, 1) should be(false)
        new Time(12, 20) before new Time(13, 10) should be(true)
      }
    }

    describe("Exercise 4: Time new implementation") {
      it("should throw an error if time is constructed with an hour outside the 0-23 range") {
        intercept[IllegalArgumentException] {
          new Time2(24, 1)
        }
        intercept[IllegalArgumentException] {
          new Time2(-1, 3)
        }
      }
      it("should throw an error if time is constructed with minutes outside 0-59 range") {
        intercept[IllegalArgumentException] {
          new Time2(12, -2)
        }
        intercept[IllegalArgumentException] {
          new Time2(11, 60)
        }
      }
      it("should be defined hours and minutes") {
        val time = new Time2(10, 23)
        time.hours should be(10)
        time.minutes should be(23)
      }
      it("before should compare dates in chronological order") {
        new Time2(10, 2) before new Time2(10, 1) should be(false)
        new Time2(12, 20) before new Time2(13, 10) should be(true)
      }
    }

  }
}
