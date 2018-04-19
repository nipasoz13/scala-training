package book.functional.programming

import book.functional.programming.datastructures.List
import book.functional.programming.datastructures.List.foldLeft

object Chapter3_Datastructures {
  /** 3.11 Write sum , product , and a function to compute the length of a list using foldLeft . **/
  def sumFl(l: List[Int]): Int = foldLeft(l, 0)(_ + _)

  def productFl(l: List[Double]): Double = foldLeft(l, 1d)(_ * _)

  def lengthFl[A](l: List[A]): Int = foldLeft(l, 0)((x, _) => x + 1)
}
