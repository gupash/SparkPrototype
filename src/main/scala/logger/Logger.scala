package logger

import org.apache.log4j.{Level, Logger}

trait Logger extends Serializable with ParentLogger {

  @transient private lazy val myCustomLogger = Logger.getLogger(this.getClass.getName)
  private lazy val isDebugEnabled = myCustomLogger.isDebugEnabled

  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("akka").setLevel(Level.ERROR)

  def debug(msg: => String): Unit =
    if (isDebugEnabled) {
      myCustomLogger.debug(msg)
    }

  def info(msg: => String): Unit = myCustomLogger.info(msg)

  def err(msg: => String): Unit = myCustomLogger.error(msg)
}
