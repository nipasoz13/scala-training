package book.functional.programming

/**
  * Created by npa on 26/06/17.
  */
object Chapter2_HigherFunctions {
  /**
    * Exercise 2.1: Write a recursive function to get the nth Fibonacci number
    */
  def fib(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, prev: Int, next: Int): Int = n match {
      case 0 => prev
      case 1 => next
      case _ => go(n - 1, next, prev + next)
    }

    go(n, 0, 1)
  }

  /**
    * Exercise 2.2: Implement isSorted , which checks whether an Array[A] is sorted according to a
    * given comparison function:
    */
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def isSorted(list: Array[A], prev: A, isSortedSoFar: Boolean): Boolean = {
      if (list.isEmpty) isSortedSoFar
      else if(!isSortedSoFar) false
      else isSorted(list.tail, list.head, isSortedSoFar && ordered(prev, list.head))
    }
    isSorted(as, as.head, true)
  }
}
