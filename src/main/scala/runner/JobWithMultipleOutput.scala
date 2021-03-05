package runner

import datasource.TableSource
import spark.SparkEngine

trait JobWithMultipleOutput extends RunJob {
  def run(sparkEngine: SparkEngine, args: Array[String]): Unit = save(logic(sparkEngine, args): _*)
  def logic(sparkEngine: SparkEngine, args: Array[String]): Seq[TableSource]
  def save(df: TableSource*): Unit
}
