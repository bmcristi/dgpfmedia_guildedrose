package com.gildedrose.domain

case class AgedBrie(var sellDays: Int, var sellQualty: Int) extends Item("Aged Brie", sellDays, sellQualty)
case class BackstagePass(var sellDays: Int, var sellQualty: Int) extends Item("Backstage passes to a TAFKAL80ETC concert", sellDays, sellQualty)
case class Sulfuras(var sellDays: Int, var sellQualty: Int) extends Item("Sulfuras, Hand of Ragnaros", sellDays, sellQualty)
case class Conjured(var sellDays: Int, var sellQualty: Int) extends Item("Conjured", sellDays, sellQualty)

