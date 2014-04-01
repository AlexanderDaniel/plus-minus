package lachdrache

import org.scalatest.FunSuite

class PlusMinusParserSuite extends FunSuite {

  private val plusMinus = new PlusMinusParser()

  test("hour/minute format for target and actual") {
    assert(Line(62, 60, -2) === plusMinus("1h2m  1h0m"))
  }

  test("7h42m is exactly one work day") {
    assert(Line(462, 462, 0) === plusMinus("1d 7h42m"))
  }

  test("38h30m is exactly one work week") {
    assert(Line(2310, 2310, 0) === plusMinus("5d 38h30m"))
  }

  test("worked half an hour more than the target") {
    assert(Line(2310, 2340, 30) === plusMinus("5d 39h0m"))
  }

  test("worked one hour less that the target") {
    assert(Line(2310, 2250, -60) === plusMinus("5d 37h30m"))
  }

  test("with optional comment") {
    assert(Line(2310, 2340, 30) === plusMinus("5d 39h0m  # comment"))
  }
}
