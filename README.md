# Anomaly Detector
Simple Anomaly Detector that helps to find problems proactively in time series data. The algorithm is based on a moving Median Absolute Deviation (MAD) window over the data. This is a relatively strongly robust measure of variation within the data, akin to a standard deviation. An outlying peak would be several MADs or more away from the median.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
Things you need to install the software:
```
java client
sbt 0.13.6+ (I believe)
```

### Installing
```
git clone https://github.com/dxdoan/anomalydetection.git
cd anomalydetection
sbt assembly
```
After this, you should have the following jar file generated: `anomalydetection/target/scala-2.12/AnomalyDetector-assembly-1.0.jar`
## Running
### Command line options
Assuming you are still in the project root directory `anomalydetection`, the program should be run with the following command:
```
java -cp target/scala-2.12/AnomalyDetector-assembly-1.0.jar com.anomalydetector.AnomalyDetector data_file_path_and_name [option(s)]
```
`option(s)` help you tune the parameters of the algorithm. You could specify none or any combination of the following 3 options:

`-act`: Anomaly Count Threshold. An integer, specifying a minimum number of anomalous data points for a data file to be considered "Anomaly Detected". Defaulted to 5.

`-ws`: Window Size. An integer, specifying the size (number of consecutive data points) of the moving MAD window used by the algorithm. Defaulted to 60 (60 data points, or 60 minutes).

`-tf`: Threshold Factor. A double, used to determine the minimum distance from the current window's median for a data point to be considered anomalous. This distance is calculated as (Threshold Factor * The median of the current window's Absolute Deviations). Threshold Factor is defaulted to 10.0.

When an option is not specified, its default value takes effect.

### Examples
```
java -cp target/scala-2.12/AnomalyDetector-assembly-1.0.jar com.anomalydetector.AnomalyDetector ~/samples/June13_data.csv
```
```
java -cp target/scala-2.12/AnomalyDetector-assembly-1.0.jar com.anomalydetector.AnomalyDetector ~/samples/June13_data.csv -tf 8 -ws 180 -act 6
```
