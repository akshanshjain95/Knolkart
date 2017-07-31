package main.scala.services

import scala.concurrent.Future
import main.scala.models._

/**
  * Created by knoldus on 31/7/17.
  */
trait InventoryServices {

  def filterItems(inventory: Inventory, category: String, itemName: String,
                  filterParameter: String): Future[List[Item]] = {
    Future {
      filterParameter.toLowerCase() match {

        case "price low to high" => inventory.listOfItems.filter(_.category == category)
          .sortBy(_.price.priceValue)

        case "price high to low" => inventory.listOfItems.filter(_.category == category)
          .sortBy(_.price.priceValue).reverse

        case "default" => inventory.listOfItems.filter(_.category == category)
      }
    }
  }

  def returnPrice(inventory: Inventory, itemID: Int): Future[Price] = Future{
    inventory.listOfItems.filter(_.itemID == itemID)(0).price
  }

  def updateItemCount(inventory: Inventory, itemID: Int, updateBy: Int)(f: (Int, Int) => Int): Future[Option[Item]] = {
    Future {
      val itemWithOldCount = inventory.listOfItems.filter(_.itemID == itemID)(0)
      if (updateBy != 0) {
        val itemWithNewCount = itemWithOldCount.copy(itemCount = f(itemWithOldCount.itemCount, updateBy))
        Some(itemWithNewCount)
      }
      else None
    }
  }

}
