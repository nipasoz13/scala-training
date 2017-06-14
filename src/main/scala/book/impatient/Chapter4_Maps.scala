package book.impatient

import java.nio.file.Path
import java.util.{Calendar, Scanner, TreeMap}

import scala.collection.JavaConverters.{mapAsScalaMapConverter, propertiesAsScalaMapConverter}
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
    val wordCount: mutable.Map[String, Int] = new TreeMap[String, Int].asScala
    while (in.hasNext()) {
      val word = in.next().capitalize
      wordCount += (word -> (wordCount.getOrElse(word, 0) + 1))
    }
    SortedMap(wordCount.toSeq: _*).withDefaultValue(0)
  }

  /**
    * 6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY , and
    * similarly for the other weekdays. Demonstrate that the elements are visited
    * in insertion order.
    */
  def calendarDaysMap(): Map[String, Int] = {
    val map = mutable.LinkedHashMap[String, Int]()
    map += ("Monday" -> Calendar.MONDAY)
    map += ("Tuesday" -> Calendar.TUESDAY)
    map += ("Wednesday" -> Calendar.WEDNESDAY)
    map += ("Thursday" -> Calendar.THURSDAY)
    map += ("Friday" -> Calendar.FRIDAY)
    map += ("Saturday" -> Calendar.SATURDAY)
    map += ("Sunday" -> Calendar.SUNDAY)
    ListMap(map.toSeq: _*)
  }

  /**
    * 7. Print a table of all Java properties reported by the getProperties method of the
    * java.lang.System class, like this:
    * java.runtime.name     | Java(TM) SE Runtime Environment
    * sun.boot.library.path | /home/apps/jdk1.6.0_21/jre/lib/i386
    * java.vm.version       | 17.0-b16
    * java.vm.vendor        | Sun Microsystems Inc.
    * java.vendor.url       | http://java.sun.com/
    * path.separator        | :
    * java.vm.name          | Java HotSpot(TM) Server VM
    *
    * You need to find the length of the longest key before you can print the table.
    */
  def getFormattedJavaProperties(): Iterable[String] = {
    mapToStringArray(getSystemProperties)
  }

  def mapToStringArray(properties: Map[String, String]): List[String] = {
    val propertyKeyMaxLength = properties.keys.map(key => key.length).max
    val array = for ((k, v) <- properties)
      yield k.padTo(propertyKeyMaxLength + 1, " ").mkString + "| " + v
    array.toList
  }

  def getSystemProperties(): Map[String, String] = {
    System.getProperties.asScala.toMap
  }

  /**
    * 8. Write a function minmax(values: Array[Int]) that returns a pair containing the
    * smallest and the largest values in the array.
    */
  def minMax(array: Array[Int]): Tuple2[Int, Int] = {
    Tuple2(array.min, array.max)
  }

  /**
    * 9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing
    * the counts of values less than v , equal to v , and greater than v .
    */
  def lteqgt(values: Array[Int], v: Int): Tuple3[Int, Int, Int] = {
    def compareToV(e: Int): Int = {
      if (e < v) -1
      else if (e == v) 0
      else 1
    }

    val groupedValues = values.groupBy(e => compareToV(e)).withDefaultValue(Array())
    Tuple3(groupedValues(-1).size, groupedValues(0).size, groupedValues(1).size)
  }

}
