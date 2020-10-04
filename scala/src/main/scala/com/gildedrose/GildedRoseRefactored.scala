package com.gildedrose

import com.gildedrose.domain._
import com.gildedrose.handlers._


class GildedRoseUpdater(implicit val sellInUpdater: SellInUpdate, implicit val qualityUpdater: QualityUpdate) {
  def update(item: Item): Unit = {
    sellInUpdater(item)
    qualityUpdater(item)
  }
}

class GildedRoseRefactored(val items: Array[Item]) {
  def updateQuality(): Unit = items.foreach {
    case item@AgedBrie(_,_) => new GildedRoseUpdater()(new RegularSellInUpdater, new IncreasingQualityUpdater()).update(item)
    case item@BackstagePass(_, _) => new GildedRoseUpdater()(new RegularSellInUpdater, new BackstagePassQualityUpdater()).update(item)
    case item@Sulfuras(_, _) => new GildedRoseUpdater()(new LegendarySellInUpdater, new LegendaryQualityUpdater()).update(item)
    case item@Conjured(_,_) => new GildedRoseUpdater()(new RegularSellInUpdater, new ConjuredQualityUpdater()).update(item)
    case item => new GildedRoseUpdater()(new RegularSellInUpdater, new RegularQualityUpdatee).update(item)
  }
}