package jobs

import datasource.DataSchema.StudentCountSchema.StudentCount
import datasource.TableSource
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import spark.SparkLocalEngine

class ExampleOneSpec extends AnyFlatSpec with Matchers {
  val sparkEngine: SparkLocalEngine = SparkLocalEngine("Test Example One")
  val spark: SparkSession = sparkEngine.spark

  import spark.implicits._

  val resultTable: TableSource = ExampleOne.logic(sparkEngine, Array(""))

  val expectedDF: DataFrame =
    Seq(StudentCount("ashish", 1), StudentCount("om", 2), StudentCount("yadarth", 1)).toDF()

  "The StudentCount result DF" should "exceptAll the expectedDF" in {
    resultTable.df.exceptAll(expectedDF).isEmpty should be(true)
  }
}
