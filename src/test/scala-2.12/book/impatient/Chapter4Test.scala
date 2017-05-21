package book.impatient

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
        discountedPrices should have size(2)
        discountedPrices("Item 1") should be(BigDecimal(9.45))
        discountedPrices("Item 2") should be(BigDecimal(135.18))
      }
    }
  }

}
