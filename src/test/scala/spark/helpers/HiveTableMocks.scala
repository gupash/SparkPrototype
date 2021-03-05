package spark.helpers

import datasource.DataSchema.StudentSchema.Student

object HiveTableMocks {

  val student = Seq(
    Student(1, "ashish", 32),
    Student(2, "om", 33),
    Student(3, "yadarth", 30),
    Student(4, "om", 31)
  )
}
