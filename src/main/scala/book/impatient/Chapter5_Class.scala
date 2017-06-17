package book.impatient

import java.time.temporal.TemporalAmount

/**
  * Created by npa on 17/06/17.
  */
object Chapter5_Class {

  /** 1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless
    * Methods,” on page 55 so that it doesn’t turn negative at Int.MaxValue . **/
  class Counter(private var value: Int = 0) {
    def increment() {
      if (value == Int.MaxValue) value = 0
      else value += 1
    }

    def current = value
  }

  /** 2. Write a class BankAccount with methods deposit and withdraw , and a read-only
    * property balance . **/
  class BankAccount {
    private var value = 0

    def balance = value

    def withdraw(amount: Int) {
      value -= amount
    }

    def deposit(amount: Int) = {
      value += amount
    }
  }

}
