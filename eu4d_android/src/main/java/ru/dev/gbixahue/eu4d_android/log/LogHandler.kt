package ru.dev.gbixahue.eu4d_android.log

/**
 * Created by Anton Zhilenkov on 10.10.17.
 */

interface LogHandler {
  fun handleLog(logMessage: String)
}
