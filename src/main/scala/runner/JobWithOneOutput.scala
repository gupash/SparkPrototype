package runner

import datasource.TableSource
import spark.SparkEngine

trait JobWithOneOutput extends RunJob {
  def run(sparkEngine: SparkEngine, args: Array[String]): Unit = save(logic(sparkEngine, args))
  def logic(sparkEngine: SparkEngine, args: Array[String]): TableSource
  def save(df: TableSource): Unit
}

