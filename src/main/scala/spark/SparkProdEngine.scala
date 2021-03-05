package spark

import org.apache.spark.sql.{DataFrame, DataFrameReader, SparkSession}

case class SparkProdEngine(appName: String, logLevel: String = "INFO") extends SparkEngine {

  val spark: SparkSession = SparkSession
    .builder
    .appName(appName)
    .enableHiveSupport()
    .getOrCreate()

  spark.sparkContext.setLogLevel(logLevel)
  spark.sqlContext.setConf("hive.exec.dynamic.partition", "true")
  spark.sqlContext.setConf("hive.exec.dynamic.partition.mode", "nonstrict")
  spark.sqlContext.setConf("spark.sql.sources.partitionOverwriteMode", "dynamic")

  override def sparkSession: SparkSession = spark

  override def loadTable(tableName: String) = {
    spark.table(tableName)
  }

  override def loadFromFile(path: String, dfr: DataFrameReader): DataFrame = {
    dfr.load(path)
  }
}
