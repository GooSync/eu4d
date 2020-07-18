package ru.gbixahue.eu4d.core.log.profiling

import ru.gbixahue.eu4d.core.log.TagLogger

/**
 * Created by Anton Zhilenkov on 03.07.18.
 */
class SimpleLogProfiler: LogProfiler {

  private val builder: StringBuilder = StringBuilder()
  private val map = mutableMapOf<String, Long>()

  private var lastLogCall = System.currentTimeMillis()

  override fun profile(from: Any, msg: String): String {
    return logAndPut(TagLogger.getTag(from), msg, System.currentTimeMillis())
  }

  private fun logAndPut(name: String, msg: String, time: Long): String {
    builder.setLength(0)
    var storedTime = map[name] ?: 0L
    map[name] = time
    val lastCallDiff = time - lastLogCall
    lastLogCall = time

    if (storedTime != 0L) storedTime = time - storedTime

    val thread = Thread.currentThread()
    builder
      .append("[").append(name).append("]:")
      .append("[").append("${thread.name}-${thread.id}").append("]")
      .append("[").append(storedTime).append("]")
      .append("(").append(lastCallDiff).append(") - ")
      .append(msg)
    return builder.toString()
  }
}

