package book.impatient

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * Created by npa on 23/04/17.
  */
object Chapter3_Arrays {
  /** 1.
    * Write a code snippet that sets a to an array of n random integers between 0
    * (inclusive) and n (exclusive) */
  def setArrayWithRandomLambda(a: Array[Int], n: Int): Unit = {
    a.transform(_ => Random.nextInt(n))
  }

  def setArrayWithRandomForLoop(a: Array[Int], n: Int): Unit = {
    for (i <- a.indices) a(i) = Random.nextInt(n)
  }

  /** 2.
    * Write a loop that swaps adjacent elements of an array of integers. For example,
    * Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5) .
    */
  def swap(a: Array[Int]): Unit = {
    for (i <- a.indices if i % 2 == 1) {
      val previous = a(i - 1)
      a(i - 1) = a(i)
      a(i) = previous
    }
  }

  /** 3.
    * Repeat the preceding assignment, but produce a new array with the swapped
    *values. Use for / yield .
    */
  def swap2(a: Array[Int]): Array[Int] = {
    (for {i <- a.indices
          x: Int = if (i % 2 == 0 && a.indices.contains(i + 1)) a(i + 1)
          else if (i % 2 == 1) a(i - 1)
          else a(i)
    } yield x).toArray
  }

  /**
    * 4. Given an array of integers, produce a new array that contains all positive
    * values of the original array, in their original order, followed by all values that
    * are zero or negative, in their original order.
    */
  def orderByParity(a: Array[Int]): Array[Int] = {
    val positive = new ArrayBuffer[Int]()
    val negative = new ArrayBuffer[Int]()

    for (elem <- a) {
      if (elem > 0) positive += elem
      else negative += elem
    }
    (positive ++ negative).toArray
  }

  /**
    * 5. How do you compute the average of an Array[Double] ?
    */
  def average(a: Array[Double]): Double = {
    a.sum / a.length
  }

  /**
    * 6. How do you rearrange the elements of an Array[Int] so that they appear in
    * reverse sorted order? How do you do the same with an ArrayBuffer[Int] ?
    */
  def reverse(a: Array[Int]): Unit = {
    val copy = a.clone()
    for (i <- copy.indices) a(a.length - 1 - i) = copy(i)
  }

  def reverse(a: ArrayBuffer[Int]): Unit = {
    a ++= a
    for (i <- a.indices if i >= a.length / 2) a(a.length - 1 - i) = a(i)
    a.trimEnd(a.length / 2)
  }

}
