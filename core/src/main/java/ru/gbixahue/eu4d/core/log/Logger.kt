package ru.gbixahue.eu4d.core.log

import ru.gbixahue.eu4d.core.log.profiling.LogProfiler

/**
 * Created by Anton Zhilenkov on 10.10.17.
 */

interface Logger {

  fun d(from: Any, msg: String, value: Any?)
  fun i(from: Any, msg: String, value: Any?)
  fun w(from: Any, msg: String, value: Any?)
  fun e(from: Any, msg: String, value: Any?)
  fun setProfiler(profiler: LogProfiler?)
  fun addHandler(handler: TypedLogHandler)
  fun removeHandler(handler: TypedLogHandler)
}
