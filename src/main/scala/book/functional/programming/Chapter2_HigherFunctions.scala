package book.functional.programming

/**
  * Created by npa on 26/06/17.
  */
object Chapter2_HigherFunctions {
  def fib(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, prev: Int, next: Int): Int = n match {
      case 0 => prev
      case 1 => next
      case _ => go(n - 1, next, prev + next)
    }

    go(n, 0, 1)
  }
}