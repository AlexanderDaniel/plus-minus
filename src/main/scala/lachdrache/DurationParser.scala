package lachdrache

import util.parsing.combinator.JavaTokenParsers

class DurationParser extends JavaTokenParsers {

  def expr: Parser[Int] = targetActual <~ comment

  def targetActual: Parser[Int] = duration ~ duration ^^ { x => x._2 - x._1 }
  def comment: Parser[String] = """.*""".r

  def duration: Parser[Int] = hourMinute | day

  def day: Parser[Int] = wholeNumber<~"d" ^^ { days => days.toInt*MinutesPerDay}
  def hourMinute: Parser[Int] = hour~minute ^^ { x => x._1*60 + x._2 }
  def hour: Parser[Int] = wholeNumber<~"h" ^^ { _.toInt }
  def minute: Parser[Int] = wholeNumber<~"m" ^^ { _.toInt }

  val MinutesPerDay = 7*60 + 42

  def apply(input :String):Int = parseAll(expr, input) match {
    case Success(result, _) => result
    case Failure(msg, _) => throw new RuntimeException(msg)
  }
}
