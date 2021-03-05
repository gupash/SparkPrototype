package datasource

object DataSchema {

  object StudentSchema extends Table {
    val tableName = "Student"
    case class Student(rollNo: Int, name: String, age: Int)
    override def colNames: TableColumns = tableColumns
    object tableColumns extends TableColumns {
      val rollNo = "rollNo"
      val name = "name"
      val age = "age"
      override def columns: Seq[String] = Seq(rollNo, name, age)
    }

  }

  object StudentCountSchema extends Table {
    val tableName = "StudentCount"
    case class StudentCount(name: String, count: Int)
    override def colNames: TableColumns = tableColumns

    object tableColumns extends TableColumns {
      val name = "name"
      val count = "count"
      override def columns: Seq[String] = Seq(name, count)
    }
  }
}
