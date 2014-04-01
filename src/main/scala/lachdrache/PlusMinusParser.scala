package lachdrache

import util.parsing.combinator.JavaTokenParsers

class PlusMinusParser extends JavaTokenParsers {

  def expr: Parser[Line] = targetActual <~ comment

  def targetActual: Parser[Line] = duration ~ duration ^^ { case target~actual => Line(target, actual, actual-target) }
  def comment: Parser[String] = """.*""".r

  def duration: Parser[Int] = day | hourMinute

  def day: Parser[Int] = num<~"d" ^^ { days => days*MinutesPerDay}
  def hourMinute: Parser[Int] = hour~minute ^^ { case h~m => h*60 + m }
  def hour: Parser[Int] = num<~"h"
  def minute: Parser[Int] = num<~"m"

  def num: Parser[Int] = wholeNumber ^^ { _.toInt }

  val MinutesPerDay = 7*60 + 42

  def apply(input :String):Line = parseAll(expr, input) match {
    case Success(result, _) => result
    case NoSuccess(msg, _) => throw new RuntimeException(msg)
  }
}
