package book.impatient

import java.nio.file.Path
import java.util.{Scanner, TreeMap}

import scala.collection.JavaConverters.mapAsScalaMapConverter
import scala.collection.immutable.{ListMap, SortedMap}
import scala.collection.mutable

/**
  * Created by npa on 21/05/17.
  */
object Chapter4_Maps {

  /**
    * 1. Set up a map of prices for a number of gizmos that you covet. Then produce
    * a second map with the same keys and the prices at a 10 percent discount.
    */
  def getDiscount(originalPrices: Map[String, BigDecimal]): Map[String, BigDecimal] = {
    for ((name, price) <- originalPrices) yield (name, price - price / 10)
  }

  /** 2. Write a program that reads words from a file. Use a mutable map to count
    * how often each word appears. To read the words, simply use a java.util.Scanner
    */
  def wordCount(filePath: Path): Map[String, Int] = {
    val in = new Scanner(filePath)
    val wordCount = mutable.Map[String, Int]().withDefaultValue(0)
    while (in.hasNext()) {
      val word = in.next().capitalize
      wordCount += (word -> (wordCount(word) + 1))
    }
    wordCount.toMap.withDefaultValue(0)
  }

  /** 3. Repeat the preceding exercise with an immutable map. */
  def wordCountImmutable(filePath: Path): Map[String, Int] = {
    val in = new Scanner(filePath)
    var wordCount = Map[String, Int]()
    while (in.hasNext()) {
      val word = in.next().capitalize
      wordCount += (word -> (wordCount.getOrElse(word, 0) + 1))
    }
    wordCount.withDefaultValue(0)
  }

  /**
    * 4. Repeat the preceding exercise with a sorted map, so that the words are
    * printed in sorted order.
    */
  def wordCountSorted(filePath: Path): Map[String, Int] = {
    val in = new Scanner(filePath)
    var wordCount = SortedMap[String, Int]()
    while (in.hasNext()) {
      val word = in.next().capitalize
      wordCount += (word -> (wordCount.getOrElse(word, 0) + 1))
    }
    wordCount.withDefaultValue(0)
  }

  /** 5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the
    * Scala API.
    */
  def wordCountTreeMap(filePath: Path): Map[String, Int] = {
    val in = new Scanner(filePath)
    val wordCount:mutable.Map[String,Int] = new TreeMap[String, Int].asScala
    while (in.hasNext()) {
      val word = in.next().capitalize
      wordCount += (word -> (wordCount.getOrElse(word, 0) + 1))
    }
    SortedMap(wordCount.toSeq:_*).withDefaultValue(0)
  }
}
