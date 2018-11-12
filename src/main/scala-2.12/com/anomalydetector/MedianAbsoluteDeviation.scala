package com.anomalydetector

/**
  * Created by dxdoan on 11/11/18.
  */
object MedianAbsoluteDeviation {
  // Get the median of a sorted array
  private def getMedian(sortedNums: Array[Double]): Double = {
    val len = sortedNums.length
    if (len % 2 == 0)
      0.5 * (sortedNums(len / 2 - 1) + sortedNums(len / 2))
    else
      sortedNums(len / 2)
  }

  private def getAbsoluteDeviations(window: Array[Double]): (Double, Array[Double]) = {
    util.Sorting.quickSort(window)

    val median = getMedian(window)
    (median, window.map(dataPoint => Math.abs(dataPoint - median)))
  }

  private[anomalydetector] def getThresholdsForWindow(fullDataPoints: Array[Double], windowEndIndex: Int, windowSize: Int, thresholdFactor: Double): (Double, Double) = {
    val window = Array.ofDim[Double](windowSize)
    Array.copy(fullDataPoints, windowEndIndex - windowSize + 1, window, 0, windowSize)

    val (median, absoluteDeviations) = getAbsoluteDeviations(window)

    util.Sorting.quickSort(absoluteDeviations)

    val absoluteDeviationsMedian = getMedian(absoluteDeviations)
    val deviationThreshold = absoluteDeviationsMedian * thresholdFactor

    (median - deviationThreshold, median + deviationThreshold)
  }
}
