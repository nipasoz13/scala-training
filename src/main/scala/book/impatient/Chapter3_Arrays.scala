package book.impatient

import java.util.TimeZone

import scala.collection.mutable.{ArrayBuffer, Buffer}
import scala.util.Random
import java.awt.datatransfer._

import scala.collection.JavaConverters._

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

  /**
    * 7. Write a code snippet that produces all values from an array with duplicates
    *removed. (Hint: Look at Scaladoc.)
    */
  def removeDuplicates[T](a: Array[T]): Array[T] = {
    a.distinct
  }

  /**
    * 8. Suppose you are given an array buffer of integers and want to remove all but
    * the first negative number. Collect positions of the negative elements, dropping the first element, reversing the
    * sequence, and calling a.remove(i) for each index
    */
  def removeAllNegativesExceptFirst(a: ArrayBuffer[Int]): Unit = {
    val negativeNumbersPositions = for (i <- a.indices if a(i) < 0) yield i
    val removalPositions = negativeNumbersPositions.drop(1).reverse
    for (index <- removalPositions) a.remove(index)
  }

  /** 9. Improve the solution of the preceding exercise by collecting the positions
    * that should be moved and their target positions. Make those moves and
    * truncate the buffer. Don’t copy any elements before the first unwanted
    * element. */
  def removeAllNegativesExceptFirstImproved(a: ArrayBuffer[Int]): Unit = {
    val negativeNumbersPositions = for (i <- a.indices if a(i) < 0) yield i
    if (negativeNumbersPositions.length >= 2) {
      val firstPositionToRemove = negativeNumbersPositions(1)
      val positionsToMove = for (i <- firstPositionToRemove until a.length if a(i) >= 0) yield i
      val targetPositions = firstPositionToRemove until firstPositionToRemove + positionsToMove.length
      for (i <- positionsToMove.indices) {
        a(targetPositions(i)) = a(positionsToMove(i))
      }
      a.trimEnd(negativeNumbersPositions.length - 1)
    }
  }


  /**
    * 10. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs
    * that are in America. Strip off the "America/" prefix and sort the result.
    */
  def getAmericanTimeZones: Array[String] = {
    val americaTzPrefix = "America/"
    TimeZone.getAvailableIDs.filter(tz => tz.startsWith(americaTzPrefix))
      .map(tz => tz.stripPrefix(americaTzPrefix)).sorted
  }

  /**
    * 11. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with
    * the call
    * val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    * Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor
    * and get the return value as a Scala buffer. (Why this obscure class? It’s hard
    * to find uses of java.util.List in the standard Java library.)
    */
  def getNativesImageFlavorsAsABuffer: Buffer[String] = {
    val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    flavors.getNativesForFlavor(DataFlavor.imageFlavor).asScala
  }

}
