package logger

trait ParentLogger {
  def debug(msg: => String): Unit
  def info(msg: => String): Unit
  def err(msg: => String): Unit
}
