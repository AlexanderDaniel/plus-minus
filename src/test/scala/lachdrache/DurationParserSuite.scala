package lachdrache

import org.scalatest.FunSuite

class DurationParserSuite extends FunSuite {

  private val durationParser = new DurationParser()

  test("durationParser") {
    assert(-2 === durationParser("1h2m  1h0m"))
    assert(0 === durationParser("1d 7h42m"))
    assert(0 === durationParser("5d 38h30m"))
    assert(30 === durationParser("5d 39h0m"))
    assert(30 === durationParser("5d 39h0m  # comment"))
    //    assert(0 === durationParser("0h0m"))
//    assert(1 === durationParser("0h1m"))
//    assert(60 === durationParser("1h0m"))
//    assert(71 === durationParser("1h11m"))
  }
}
