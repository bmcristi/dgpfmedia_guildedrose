package com.gildedrose.handlers

import com.gildedrose.domain.Item

import scala.math.{max, min}

trait QualityUpdate {
  def apply(item: Item)
}

//This is to be used for regular updates
class RegularQualityUpdatee extends QualityUpdate {
  def apply(item: Item): Unit = {
    val qualityDecrease = if (item.sellIn >= 0) 1 else 2
    item.quality = max(item.quality - qualityDecrease, 0)
  }
}

//This is a generic updater to be used for Brie, or other items which might increase in value as the time goes by
class IncreasingQualityUpdater extends QualityUpdate {
  def apply(item: Item): Unit = {
    item.quality = min(item.quality + 1, 50)
  }
}

//This one has the risk of getting to 50
class BackstagePassQualityUpdater extends QualityUpdate {
  def apply(item: Item): Unit = {
    item.quality =
      min(if (item.sellIn > 10) item.quality + 1
      else if (item.sellIn > 5) item.quality + 2
      else if (item.sellIn > 0) item.quality + 3
      else 0,
        50)
  }
}

//This one never changes (Legendary items)
class LegendaryQualityUpdater extends QualityUpdate {
  def apply(item: Item): Unit = {
    item.quality = item.quality
  }
}

//New item added -  quality decreases 2x times as regular items
class ConjuredQualityUpdater extends QualityUpdate {
  def apply(item: Item): Unit = {
    val qualityDecrease = if (item.sellIn >= 0) 2 else 4
    item.quality = max(item.quality - qualityDecrease, 0)
  }
}


