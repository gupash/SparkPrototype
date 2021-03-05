package runner

import spark.SparkEngine

trait RunJob {
  def run(sparkEngine: SparkEngine, args: Array[String]): Unit
}
