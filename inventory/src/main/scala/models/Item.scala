package main.scala.models

case class Item(itemID: Int, category: String, itemName: String, description: String, photos: List[Photo],
                vendorInformation: Vendor, price: Price, itemCount: Int)
