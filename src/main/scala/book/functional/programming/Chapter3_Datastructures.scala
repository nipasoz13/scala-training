package book.functional.programming

import book.functional.programming.datastructures.List
import book.functional.programming.datastructures.List._

object Chapter3_Datastructures {
  /** 3.11 Write sum, product and a function to compute the length of a list using foldLeft . **/
  def sumFl(l: List[Int]): Int = foldLeft(l, 0)(_ + _)

  def productFl(l: List[Double]): Double = foldLeft(l, 1d)(_ * _)

  def lengthFl[A](l: List[A]): Int = foldLeft(l, 0)((x, _) => x + 1)

  /** 3.12 Write a function that returns the reverse of a list (given List(1,2,3) it returns
    List(3,2,1)). See if you can write it using a fold. **/
  def reverseFl[A](l: List[A]): List[A] = foldLeft(l, List[A]())((z,x) => append(List(x), z))
}
