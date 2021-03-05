package jobs

import datasource.DataSchema.StudentSchema.Student
import datasource.DataSchema.StudentSchema.{tableColumns => S}
import datasource.DataSources.StudentCountTbl
import datasource.TableSource
import org.apache.spark.sql.SaveMode
import runner.JobWithOneOutput
import spark.{SparkEngine, SparkProdEngine}

object ExampleOne extends JobWithOneOutput {

  override def logic(sparkEngine: SparkEngine, args: Array[String]): TableSource = {

    val spark = sparkEngine.sparkSession
    import spark.implicits._
    val studentData = sparkEngine
      .loadFromFile(
        "src/main/resources/students.tsv",
        spark.read
          .option("delimiter", "\t")
          .option("inferSchema", "true")
          .format("com.databricks.spark.csv")
      )
      .toDF(S.columns: _*)
      .as[Student]

    val studentsWithSameName = studentData.select(S.name).groupBy("name").count()
    StudentCountTbl(studentsWithSameName)
  }
  override def save(table: TableSource): Unit =
    table.df.write.format("orc").mode(SaveMode.Overwrite).insertInto(table.name)

  def main(args: Array[String]): Unit =
    run(SparkProdEngine("ExampleOne"), args)
}
