package com.anomalydetector

object AnomalyDetector {
  def readFile(fileName: String): List[(String, Int)] = io.Source.fromFile(fileName)
    .getLines
    .map(_.split(",").map(_.trim))
    .map(cols => (cols(0), cols(1).toInt))
    .toList

  def main(args: Array[String]) = {
    println(readFile(args(0)))
  }
}