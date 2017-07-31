package main.scala.services

import main.scala.models.{Inventory, Item}


trait BackendService {

  def addItemToInventory(inventory: Inventory, item: Item): (Inventory, Int) = {

    val listOfItems = inventory.listOfItems
    val newList = item :: listOfItems
    (Inventory(newList), item.itemID)

  }

}
