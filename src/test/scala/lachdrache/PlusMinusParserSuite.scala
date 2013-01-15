package lachdrache

import org.scalatest.FunSuite

class PlusMinusParserSuite extends FunSuite {

  private val plusMinus = new PlusMinusParser()

  test("hour/minute format for target and actual") {
    assert(-2 === plusMinus("1h2m  1h0m"))
  }

  test("7h42m is exactly one work day") {
    assert(0 === plusMinus("1d 7h42m"))
  }

  test("38h30m is exactly one work week") {
    assert(0 === plusMinus("5d 38h30m"))
  }

  test("worked half an hour more than the target") {
    assert(30 === plusMinus("5d 39h0m"))
  }

  test("worked one hour less that the target") {
    assert(-60 === plusMinus("5d 37h30m"))
  }

  test("with optional comment") {
    assert(30 === plusMinus("5d 39h0m  # comment"))
  }
}
