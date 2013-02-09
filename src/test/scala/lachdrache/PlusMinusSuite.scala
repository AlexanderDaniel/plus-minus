package lachdrache

import org.scalatest.FunSuite
import PlusMinus.formatMinutes
import PlusMinus.generateOutput
import java.text.NumberFormat

class PlusMinusSuite extends FunSuite {

  test("formatMinutes") {
    assert(formatMinutes(3) === "0h3m")
    assert(formatMinutes(60) === "1h0m")
    assert(formatMinutes(119) === "1h59m")
    assert(formatMinutes(-1) === "-0h1m")
    assert(formatMinutes(-61) === "-1h1m")
    assert(formatMinutes(0) === "0h0m")
  }

  test("generateOutput with plus") {
    val result = generateOutput(Vector("1d 8h0m"))
    val expected = Vector(
      "  18 - 1d 8h0m",
      "====",
      f"  18 = 0h18m = ${0.30}%.2f"
    )
    assert(result === expected)
  }

  test("generateOutput with minus") {
    val result = generateOutput(Vector("1d 7h0m"))
    val expected = Vector(
      " -42 - 1d 7h0m",
      "====",
      f" -42 = -0h42m = ${-0.70}%.2f"
    )
    assert(result === expected)
  }

  test("generateOutput with minus (more than 1 hour)") {
    val result = generateOutput(Vector("1d 6h0m"))
    val expected = Vector(
      "-102 - 1d 6h0m",
      "====",
      f"-102 = -1h42m = ${-1.70}%.2f"
    )
    assert(result === expected)
  }

}
