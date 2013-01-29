package lachdrache

import util.parsing.combinator.JavaTokenParsers

class PlusMinusParser extends JavaTokenParsers {

  def expr: Parser[Int] = targetActual <~ comment

  def targetActual: Parser[Int] = duration ~ duration ^^ { case target~actual => actual-target }
  def comment: Parser[String] = """.*""".r

  def duration: Parser[Int] = day | hourMinute

  def day: Parser[Int] = wholeNumber<~"d" ^^ { days => days.toInt*MinutesPerDay}
  def hourMinute: Parser[Int] = hour~minute ^^ { case h~m => h*60 + m }
  def hour: Parser[Int] = wholeNumber<~"h" ^^ { _.toInt }
  def minute: Parser[Int] = wholeNumber<~"m" ^^ { _.toInt }

  val MinutesPerDay = 7*60 + 42

  def apply(input :String):Int = parseAll(expr, input) match {
    case Success(result, _) => result
    case NoSuccess(msg, _) => throw new RuntimeException(msg)
    case Failure(msg, _) => throw new RuntimeException(msg)
  }
}
