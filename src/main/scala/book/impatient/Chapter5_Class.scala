package book.impatient

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

    // Methods are public by default
    def current = value
  }

}
