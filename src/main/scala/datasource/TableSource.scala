package datasource

import org.apache.spark.sql.DataFrame

trait TableSource {
  def name: String
  def df: DataFrame
}
