package com.gildedrose

import com.gildedrose.TestingUtils.UnitTest
import com.gildedrose.domain._
import org.scalatest.{BeforeAndAfterEach, FunSpec}

class GildedRoseTestRefactored extends FunSpec with BeforeAndAfterEach {

  var gildedRoseApp: GildedRoseRefactored = null
  var regularItem, agedBrie, sulfuras, backstagePass, conjured: Item = null

  override def beforeEach(): Unit = {
    regularItem = new Item("regular", 100, 50)
    agedBrie = new AgedBrie( 100, 49)
    backstagePass = new BackstagePass(100, 47)
    conjured = new Conjured( 100, 50)
    sulfuras = new Sulfuras( 100, 80)
    gildedRoseApp = new GildedRoseRefactored(Array(regularItem, agedBrie, sulfuras, backstagePass, conjured))
  }

  describe("When updating a regular item") {
    it(" should lower sell in value by 1", UnitTest) {
      gildedRoseApp.updateQuality()
      assert(regularItem.sellIn == 99)
    }
    it(" should lower the quality in value by 1", UnitTest) {
      gildedRoseApp.updateQuality()
      assert(regularItem.quality == 49)
    }
    it(" should never lower the quality below 0 ", UnitTest) {
      regularItem.quality = 0
      gildedRoseApp.updateQuality()
      assert(regularItem.quality == 0)
    }
    it(" should lower the quality by 2x when sellIn date is below 0 ", UnitTest) {
      regularItem.sellIn = 0
      gildedRoseApp.updateQuality()
      assert(regularItem.quality == 48)
    }

  }

  describe(" When updating Aged Brie") {
    it(" should increase the quality", UnitTest) {
      gildedRoseApp.updateQuality()
      assert(agedBrie.quality == 50)
    }
    it(" should not increase the quality by more then 50", UnitTest) {
      agedBrie.quality = 50
      gildedRoseApp.updateQuality()
      assert(agedBrie.quality == 50)
    }
  }

  describe(" When updating backstagePass") {
    it(" should increase the quality by 1 when sellIn > 10 days", UnitTest) {
      gildedRoseApp.updateQuality()
      assert(backstagePass.quality == 48)
    }
    it(" should increase the quality by 2 when sellIn < 10 days ", UnitTest) {
      backstagePass.sellIn = 10
      gildedRoseApp.updateQuality()
      assert(backstagePass.quality == 49)
    }
    it(" should increase the quality by 3 when sellIn <= 5 days ", UnitTest) {
      backstagePass.sellIn = 5
      gildedRoseApp.updateQuality()
      assert(backstagePass.quality == 50)
    }

    it(" should not increase the quality more than 50 ", UnitTest) {
      backstagePass.sellIn = 5
      backstagePass.quality = 50
      gildedRoseApp.updateQuality()
      assert(backstagePass.quality == 50)
    }

    it(" should drop the quality to 0 when they have expired", UnitTest) {
      backstagePass.sellIn = 0
      gildedRoseApp.updateQuality()
      assert(backstagePass.quality == 0)
    }

  }

  describe(" When updating a legacy item") {
    it(" should not decrease the quality ", UnitTest) {
      gildedRoseApp.updateQuality()
      assert(sulfuras.quality == 80)
    }
    it(" should not decrease the sellIn", UnitTest) {
      gildedRoseApp.updateQuality()
      assert(sulfuras.sellIn == 100)
    }

  }
  //These tests will fail because Conjured is not implemented in the initial class
  describe(" When updating a conjured item") {
    it(" should decrease the quality by 2  ", UnitTest) {
      gildedRoseApp.updateQuality()
      assert(conjured.quality == 48)
    }
    it(" should decrease the quality by 4 when sellIn is 0", UnitTest) {
      conjured.sellIn = 0
      gildedRoseApp.updateQuality()
      assert(conjured.quality == 46)
    }

  }

}