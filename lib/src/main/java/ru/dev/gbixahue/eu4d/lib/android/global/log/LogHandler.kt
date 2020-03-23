package ru.dev.gbixahue.eu4d.lib.android.global.log

/**
 * Created by Anton Zhilenkov on 10.10.17.
 */

interface LogHandler {
  fun handleLog(logMessage: String, type: LogType)
}

interface TypedLogHandler: LogHandler {
  fun getType(): LogType
}

open class MultiLogHandler(
    private val handledTypes: MutableList<TypedLogHandler>
): LogHandler {

  override fun handleLog(logMessage: String, type: LogType) {
    handledTypes.filter { it.getType() == type }.forEach { it.handleLog(logMessage, type) }
  }
}
