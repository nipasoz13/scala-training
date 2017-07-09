package book.functional.programming.datastructures

/**
  * Created by npa on 6/07/17.
  */
sealed trait List[+A]

// `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing]

// A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match {
    // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x, xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  /** 3.1: Implement the function tail for removing the first element of a List . Note that the
    * function takes constant time. **/
  def tail[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case Cons(_, xs) => xs
  }

  /**
    * 3.3: Using the same idea, implement the function setHead for replacing the first element
    * of a List with a different value.
    */
  def setHead[A](head: A, as: List[A]): List[A] = as match {
    case Nil => Cons(head, Nil)
    case Cons(_, xs) => Cons(head, xs)
  }

  /** 3.4: Generalize tail to the function drop , which removes the first n elements from a list.
    * Note that this function takes time proportional only to the number of elements being
    * dropped—we don’t need to make a copy of the entire List . **/
  @annotation.tailrec
  def drop[A](l: List[A], n: Int): List[A] = {
    if (l == Nil) Nil
    else if (n == 0) l
    else drop(tail(l), n - 1)
  }

}
