package lachdrache

import io.{BufferedSource, Source}


object PlusMinus {

  def main(args: Array[String]) {
    val durationParser = new DurationParser
    val file: BufferedSource = Source.fromFile(args(0))
    val result = file.getLines().map(line => (durationParser(line), line)).toList
    val sum = result.map(_._1).sum

    result.foreach(r => println("%4d - %s".format(r._1, r._2)))
    println("====")
    println("%4d = %s = %.2f".format(sum, formatMinutes(sum), formatMinutesAsDecimal(sum)))
  }

  private def formatMinutes(input: Int): String = {
    val hours = input / 60
    val minutes = input % 60
    "%dh%dm".format(hours, minutes)
  }

  private def formatMinutesAsDecimal(input: Int): Double = {
    input.toDouble / 60.0
  }

}
