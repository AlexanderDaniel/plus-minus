package lachdrache

case class Line(target: Int, actual: Int, plusMinus: Int) {
  
  def +(other: Line): Line =
    Line(target + other.target, actual + other.actual, plusMinus + other.plusMinus)
}
