package book.impatient

import book.impatient.Chapter5_Class.{BankAccount, Counter}
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

  }
}
