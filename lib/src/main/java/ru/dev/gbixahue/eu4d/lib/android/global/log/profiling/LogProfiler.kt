package ru.dev.gbixahue.eu4d.lib.android.global.log.profiling

/**
 * Created by Anton Zhilenkov on 03.07.18.
 */
interface LogProfiler {
  fun profile(from: Any, msg: String): String
}