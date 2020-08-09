package ru.gbixahue.eu4d.android.log

import ru.gbixahue.eu4d.core.global.function.stringOf
import ru.gbixahue.eu4d.core.log.LogType
import ru.gbixahue.eu4d.core.log.Logger
import ru.gbixahue.eu4d.core.log.TypedLogHandler
import ru.gbixahue.eu4d.core.log.profiling.LogProfiler
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * Created by Anton Zhilenkov on 10.10.17.
 */

open class TagLogger(
  private val tag: String,
  executorLogger: ExecutorService = Executors.newFixedThreadPool(1),
  executorHandlers: ExecutorService? = null
): Logger {

  protected var msProfiler: LogProfiler? = null
  protected var handlerList: MutableList<TypedLogHandler> = mutableListOf()

  private val logExecutor: ExecutorService = executorLogger
  private val handlersExecutor: ExecutorService? = executorHandlers ?: executorLogger
  private val builder = StringBuilder()

  override fun d(from: Any, msg: String, value: Any?) {
    print(from, msg, value, LogType.DEBUG)
  }

  override fun i(from: Any, msg: String, value: Any?) {
    print(from, msg, value, LogType.INFO)
  }

  override fun w(from: Any, msg: String, value: Any?) {
    print(from, msg, value, LogType.WARNING)
  }

  override fun e(from: Any, msg: String, value: Any?) {
    print(from, msg, value, LogType.ERROR)
  }

  override fun setProfiler(profiler: LogProfiler?) {
    msProfiler = profiler
  }

  override fun addHandler(handler: TypedLogHandler) {
    handlerList.add(handler)
  }

  override fun removeHandler(handler: TypedLogHandler) {
    handlerList.remove(handler)
  }

  protected open fun print(from: Any, msg: String, data: Any?, type: LogType) {
    val logFrom = buildFrom(from)
    val logMessage = buildLogMessage(msg, data)
    val logFromMessage = logFrom + logMessage

    val completeMessage = msProfiler?.profile(from, logMessage) ?: logFromMessage
    logExecutor.run { androidLog(completeMessage, type) }
    handlersExecutor.run { handlerList.forEach { it.handleLog(completeMessage, type) } }
  }

  protected open fun buildLogMessage(msg: String, data: Any?): String {
    if (builder.capacity() > 0) builder.setLength(0)

    builder.append(msg)
    data?.let { builder.append(" {").append(stringOf(it)).append("}") }
    return builder.toString()
  }

  private fun buildFrom(from: Any?): String = "[${getTag(
    from
  )}]: "

  private fun androidLog(message: String, type: LogType) {
    when (type) {
      LogType.DEBUG -> android.util.Log.d(tag, message)
      LogType.WARNING -> android.util.Log.w(tag, message)
      LogType.ERROR -> android.util.Log.e(tag, message)
      LogType.INFO -> android.util.Log.i(tag, message)
    }
  }

  companion object {
    fun getTag(from: Any?): String {
      if (from == null) return "UNKNOWN"
      return when (from) {
        is CharSequence -> from.toString()
        else -> from.javaClass.simpleName
      }
    }
  }
}
