package com.gildedrose

/**
  * Util class for testing - provides tags
  */
object TestingUtils {

  import org.scalatest.Tag

  object UnitTest extends Tag("UnitTest")

  object IntegrationTest extends Tag("IntegrationTest")

}