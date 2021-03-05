package datasource

trait Table {
  def tableName: String
  def colNames: TableColumns

  trait TableColumns {
    def columns: Seq[String]
  }
}
