package com.gildedrose.handlers

import com.gildedrose.domain.Item

trait SellInUpdate {
  def apply(item: Item)
}

class RegularSellInUpdater extends SellInUpdate {
  def apply(item: Item): Unit = {
    item.sellIn = item.sellIn - 1
  }
}

class LegendarySellInUpdater extends SellInUpdate {
  def apply(item: Item): Unit = {
    item.sellIn = item.sellIn
  }
}