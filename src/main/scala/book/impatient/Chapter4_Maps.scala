package book.impatient

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
}
