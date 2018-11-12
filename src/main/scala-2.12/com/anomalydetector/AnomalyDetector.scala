package com.anomalydetector

import com.anomalydetector.InputParser.{parseInputs, readFile}
import com.anomalydetector.MedianAbsoluteDeviation.getThresholdsForWindow

object AnomalyDetector {
  private def getAnomalies(fullRecordings: Array[(String, Double)], windowSize: Int, thresholdFactor: Double): Array[(String, Double)] = {
    val fullDataPoints = fullRecordings.map(_._2)

    val (initialWindowLowerBound, initialWindowUpperBound) = getThresholdsForWindow(fullDataPoints, windowSize - 1, windowSize, thresholdFactor)

    fullRecordings.zipWithIndex.filter({ case ((_, recording), index) =>
      if (index < windowSize)
        recording < initialWindowLowerBound || recording > initialWindowUpperBound
      else {
        val (lowerBound, upperBound) = getThresholdsForWindow(fullDataPoints, index, windowSize, thresholdFactor)
        recording < lowerBound || recording > upperBound
      }
    }).unzip._1
  }

  def main(args: Array[String]) = {
    val (fileName, anomalyCountThreshold, windowSize, thresholdFactor) = parseInputs(args)

    val anomalies = getAnomalies(readFile(fileName), windowSize, thresholdFactor)

    if (anomalies.length < anomalyCountThreshold)
      println("No Anomalies")
    else {
      println("Anomaly Detected!!!\nThere are " + anomalies.length + " suspicious data points. Here they are:")
      anomalies.foreach(println(_))
    }
  }
}