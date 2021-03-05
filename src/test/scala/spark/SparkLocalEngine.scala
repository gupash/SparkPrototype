package spark

import org.apache.spark.sql.{DataFrame, DataFrameReader, SparkSession}
import spark.helpers.HiveTableMocks

case class SparkLocalEngine(appName: String, logLevel: String = "INFO") extends SparkEngine {

  val spark: SparkSession =
    SparkSession.builder.appName(s"$appName local").master("local[*]").getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")

  import spark.implicits._

  override def sparkSession: SparkSession = spark

  override def loadTable(tableName: String): DataFrame =
    tableName match {
      case "Student" => HiveTableMocks.student.toDF
      case _ =>
        val path = tableName.replace('.', '_')
        spark.read.parquet(s"spark-warehouse/tables/$path")
    }

  override def loadFromFile(path: String, dfr: DataFrameReader): DataFrame = {

    val fileName = path.split("/").reverse.head.split("\\.")(0)

    fileName match {
      case name if name == "student" || name == "students" => HiveTableMocks.student.toDF
      case allOtherNames =>
        val path = allOtherNames.replace('.', '_')
        spark.read.parquet(s"spark-warehouse/tables/$path")
    }
  }
}
