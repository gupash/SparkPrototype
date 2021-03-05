package datasource

import datasource.DataSchema.{StudentSchema, StudentCountSchema}
import org.apache.spark.sql.DataFrame
import spark.SparkEngine

object DataSources {
  case class StudentTbl(df: DataFrame) extends TableSource {
    override def name: String = StudentSchema.tableName
  }
  case class StudentCountTbl(df: DataFrame) extends TableSource {
    override def name: String = StudentCountSchema.tableName
  }
}

case class DataSources(spark: SparkEngine) {
  def student(spark: SparkEngine): DataFrame = spark.loadTable(StudentSchema.tableName)
  def studentCount(spark: SparkEngine): DataFrame = spark.loadTable(StudentCountSchema.tableName)
}
