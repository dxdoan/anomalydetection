package com.anomalydetector

/**
  * Created by dxdoan on 11/11/18.
  */
object InputParser {
  private val defaultAnomalyCountThreshold = 5
  private val defaultWindowSize = 60
  private val defaultThresholdFactor = 10.0

  private[anomalydetector] def parseInputs(args: Array[String]): (String, Int, Int, Double) = {
    val anomalyCountThresholdPos = args.indexWhere(_ == "-act") + 1
    val anomalyCountThreshold = if (anomalyCountThresholdPos > 1) args(anomalyCountThresholdPos).toInt else defaultAnomalyCountThreshold

    val windowSizePos = args.indexWhere(_ == "-ws") + 1
    val windowSize = if (windowSizePos > 1) args(windowSizePos).toInt else defaultWindowSize

    val thresholdFactorPos = args.indexWhere(_ == "-tf") + 1
    val thresholdFactor = if (thresholdFactorPos > 1) args(thresholdFactorPos).toDouble else defaultThresholdFactor

    (args(0), anomalyCountThreshold, windowSize, thresholdFactor)
  }

  private[anomalydetector] def readFile(fileName: String): Array[(String, Double)] = io.Source.fromFile(fileName)
    .getLines
    .map(_.split(",").map(_.trim))
    .map(cols => (cols(0), cols(1).toDouble))
    .toArray
}
