package spark

import org.apache.spark.sql.{DataFrame, DataFrameReader, SparkSession}

trait SparkEngine {

  type Pair = (String, String)

  def sparkSession: SparkSession
  def loadTable(tableName: String): DataFrame
  def loadFromFile(path: String, dfr: DataFrameReader): DataFrame
}
