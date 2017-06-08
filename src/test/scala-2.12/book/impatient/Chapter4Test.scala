package book.impatient

import java.nio.file.{Path, Paths}
import java.util.Calendar._

import book.impatient.Chapter4_Maps._
import org.scalatest.{FunSpec, Matchers}

import scala.collection.mutable

/**
  * Created by npa on 21/05/17.
  */
class Chapter4Test extends FunSpec with Matchers {
  describe("Chapter 4: Maps and Tuples") {
    describe("Exercise 1:") {
      it("should create a discount of 10%") {
        val originalPrices = Map("Item 1" -> BigDecimal(10.5), "Item 2" -> BigDecimal(150.2))
        val discountedPrices = getDiscount(originalPrices)
        discountedPrices should have size 2
        discountedPrices("Item 1") should be(BigDecimal(9.45))
        discountedPrices("Item 2") should be(BigDecimal(135.18))
      }
    }

    describe("Exercise 2") {
      it("should count words of a file") {
        val testFilePath = getFilePathFromClassPath("wordCountTest.txt")
        val wordCountResult = wordCount(testFilePath)
        wordCountResult("For") should be(2)
        wordCountResult("Test") should be(2)
        wordCountResult("Hello") should be(1)
        wordCountResult("Java") should be(0)
      }
    }

    describe("Exercise 3") {
      it("should count words of a file") {
        val testFilePath = getFilePathFromClassPath("wordCountTest.txt")
        val wordCountResult = wordCountImmutable(testFilePath)
        wordCountResult("For") should be(2)
        wordCountResult("Test") should be(2)
        wordCountResult("Hello") should be(1)
        wordCountResult("Java") should be(0)
      }
    }

    describe("Exercise 4") {
      val testFilePath = getFilePathFromClassPath("wordCountTest.txt")
      val wordCountResult = wordCountSorted(testFilePath)

      it("should count words of a file") {
        wordCountResult("For") should be(2)
        wordCountResult("Test") should be(2)
        wordCountResult("Hello") should be(1)
        wordCountResult("Java") should be(0)
      }

      it("should return a map sorted by word") {
        wordCountResult.keySet.toList shouldBe sorted
      }
    }

    describe("Exercise 5: Sorted word count with Java treeMap") {
      val testFilePath = getFilePathFromClassPath("wordCountTest.txt")
      val wordCountResult = wordCountTreeMap(testFilePath)

      it("should count words of a file") {
        wordCountResult("For") should be(2)
        wordCountResult("Test") should be(2)
        wordCountResult("Hello") should be(1)
        wordCountResult("Java") should be(0)
      }

      it("should return a map sorted by word") {
        wordCountResult.keySet.toList shouldBe sorted
      }
    }

    describe("Exercice 6: Map to days to their Calendar integer code") {
      val map = calendarDaysMap
      it("should be ordered by days from Monday to Sunday") {
        val keys = Set("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        map.keys should be(keys)
      }

      it("should map day to its Calendar code") {
        map("Monday") should be(MONDAY)
        map("Tuesday") should be(TUESDAY)
        map("Wednesday") should be(WEDNESDAY)
        map("Thursday") should be(THURSDAY)
        map("Friday") should be(FRIDAY)
        map("Saturday") should be(SATURDAY)
        map("Sunday") should be(SUNDAY)
      }
    }

    describe("Exercise 7: Print java properties") {
      val mockedProperties = mutable.Map("java.runtime.name" -> "Java(TM) SE Runtime Environment")
      mockedProperties += ("sun.boot.library.path" -> "/home/apps/jdk1.6.0_21/jre/lib/i386")
      mockedProperties += ("java.vm.name" -> "Java HotSpot(TM) Server VM")

      ignore("should return a formatted list of java properties") {
        //TODO: Try to mock either System.getProperties or scala conversion

        val javaProperties = getFormattedJavaProperties();
        javaProperties should contain("java.runtime.name     | Java(TM) SE Runtime Environment")
        javaProperties should contain("sun.boot.library.path | /home/apps/jdk1.6.0_21/jre/lib/i386")
        javaProperties should contain("java.vm.name          | Java HotSpot(TM) Server VM")
      }

      it("should format map") {
        val formattedKeyValues = mapToStringArray(mockedProperties.toMap)
        formattedKeyValues should contain("java.runtime.name     | Java(TM) SE Runtime Environment")
        formattedKeyValues should contain("sun.boot.library.path | /home/apps/jdk1.6.0_21/jre/lib/i386")
        formattedKeyValues should contain("java.vm.name          | Java HotSpot(TM) Server VM")
      }
    }

    describe("Exercise 8: MinMax function") {
      it("should returns a pair with the min and max of a list of integers") {
        val minMaxResult = minMax(Array(1, 2, 3, 4))
        minMaxResult should be(Tuple2(1, 4))
      }
    }
  }

  def getFilePathFromClassPath(filePath: String): Path = {
    Paths.get(getClass.getClassLoader.getResource(filePath).getPath)
  }
}
