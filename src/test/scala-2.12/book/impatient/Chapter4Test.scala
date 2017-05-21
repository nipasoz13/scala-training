package book.impatient

import java.nio.file.{Path, Paths}

import org.scalatest.{FunSpec, Matchers}
import Chapter4_Maps._


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
  }

  def getFilePathFromClassPath(filePath: String): Path = {
    Paths.get(getClass.getClassLoader.getResource(filePath).getPath)
  }
}
