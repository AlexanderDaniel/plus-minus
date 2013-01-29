package lachdrache

import org.scalatest.FunSuite
import PlusMinus.formatMinutes

class PlusMinusSuite extends FunSuite {

  test("formatMinutes") {
    assert(formatMinutes(3) === "0h3m")
    assert(formatMinutes(60) === "1h0m")
    assert(formatMinutes(119) === "1h59m")
  }

}
