package lachdrache

import io.{BufferedSource, Source}


object PlusMinus {

  def main(args: Array[String]) {
    val file: BufferedSource = Source.fromFile(args(0))
    generateOutput(file.getLines().toVector) foreach println
  }

  def generateOutput(input: Vector[String]): Vector[String] = {
    val durationParser = new PlusMinusParser
    val plusMinusPerDay = input map (line => durationParser(line))
    val sum = plusMinusPerDay.sum
    val output = plusMinusPerDay zip input map {
      case (minutes, line) => "%4d - %s".format(minutes, line)
    }
    output :+ "====" :+ "%4d = %s = %.2f".format(sum, formatMinutes(sum), minutesAsDecimal(sum))
  }

  def formatMinutes(input: Int): String = {
    s"${input / 60}h${input % 60}m"
  }

  def minutesAsDecimal(input: Int): Double = {
    input.toDouble / 60.0
  }

}
