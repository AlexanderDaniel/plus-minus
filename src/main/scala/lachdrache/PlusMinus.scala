package lachdrache

import io.{BufferedSource, Source}


object PlusMinus {

  def main(args: Array[String]) {
    val file: BufferedSource = Source.fromFile(args(0))
    generateOutput(file.getLines().toVector) foreach println
  }

  def generateOutput(input: Vector[String]): Vector[String] = {
    val durationParser = new PlusMinusParser
    val perDay = input map (line => durationParser(line))
    val plusMinusPerDay = perDay map (line => line.plusMinus)
    val sum = perDay.reduceLeft { (z, day) =>
      z + day
    }
    val output = plusMinusPerDay zip input map {
      case (minutes, line) => f"$minutes%4d - $line"
    }
    output :+ "====" :+ f"${sum.plusMinus}%4d = ${formatMinutes(sum.plusMinus)} = ${minutesAsDecimal(sum.plusMinus)}%.2f (${formatMinutes(sum.actual)} - ${formatMinutes(sum.target)})"
  }

  def formatMinutes(input: Int): String =
    sign(input) + formatPositiveMinutes(math.abs(input))

  def sign(n: Int): String =
    if (n<0) "-" else ""

  def formatPositiveMinutes(input: Int): String = {
    require(input>=0)
    s"${input / 60}h${input % 60}m"
  }

  def minutesAsDecimal(input: Int): Double = {
    input.toDouble / 60.0
  }

}
