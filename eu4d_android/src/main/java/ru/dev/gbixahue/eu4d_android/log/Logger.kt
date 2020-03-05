package ru.dev.gbixahue.eu4d_android.log

import ru.dev.gbixahue.eu4d_android.log.profiling.LogProfiler

/**
 * Created by Anton Zhilenkov on 10.10.17.
 */

interface Logger {

  fun d(from: Any, msg: String, value: Any?)
  fun w(from: Any, msg: String, value: Any?)
  fun e(from: Any, msg: String, value: Any?)
  fun setProfiler(profiler: LogProfiler?)
  fun addHandler(handler: LogHandler?)
}
