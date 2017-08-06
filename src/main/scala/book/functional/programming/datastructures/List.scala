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

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

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
  def drop[A](l: List[A], n: Int): List[A] =
  if (l == Nil) Nil
  else if (n == 0) l
  else drop(tail(l), n - 1)

  /** 3.5 Implement dropWhile , which removes elements from the List prefix as long as they
    * match a predicate. **/
  @annotation.tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case Cons(x, xs) if !f(x) => Cons(x, xs)
    case Cons(x, xs) if f(x) => dropWhile(xs, f)
  }

  /** 3.6 Not everything works out so nicely. Implement a function, init , that returns a List
    * consisting of all but the last element of a List . So, given List(1,2,3,4) , init will
    * return List(1,2,3) . Why can’t this function be implemented in constant time like
    * tail ? **/
  def init[A](l: List[A]): List[A] = {
    @annotation.tailrec
    def init[A](result: List[A], tail: List[A]): List[A] = tail match {
      case Nil => Nil
      case Cons(_, Nil) => result
      case Cons(x, xs) => init(append(result, Cons(x, Nil)), xs)
    }

    init(Nil, l)
  }

  /** 3.9: Compute the length of a list using foldRight **/
  def length[A](l: List[A]): Int = foldRight(l, 0)((_, y) => y + 1)

  /**
    * 3.10: Our implementation of foldRight is not tail-recursive and will result in a StackOver-
    * flowError for large lists (we say it’s not stack-safe). Convince yourself that this is the
    * case, and then write another general list-recursion function, foldLeft, that is tail-recursive,
    * using the techniques we discussed in the previous chapter. Here is its signature:
    */
  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = {
    @annotation.tailrec
    def go(tail: List[A], result: B): B = tail match {
      case Nil => result
      case Cons(x, xs) => go(xs, f(result, x))
    }
    go(l, z)
  }
}
