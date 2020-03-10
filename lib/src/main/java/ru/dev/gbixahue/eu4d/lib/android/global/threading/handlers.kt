package ru.dev.gbixahue.eu4d.lib.android.global.threading

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

/**
 * Created by Anton Zhilenkov on 20.08.18.
 */

class ThreadHandler {
  val ui: Handler = Handler(Looper.getMainLooper())
  val work: Handler = Handler(HandlerThread("Application worker thread").apply { start() }.looper)

  fun postInCurrentThread(msTime: Long, func: () -> Unit) {
    Handler().postDelayed({ func() }, msTime)
  }
}

internal class LazyInstance {
  companion object {
    val handler: ThreadHandler by lazy { ThreadHandler() }
  }
}

fun post(msTime: Long, func: () -> Unit) {
  LazyInstance.handler.postInCurrentThread(msTime, func)
}

fun postUI(msTime: Long = 0, func: () -> Unit) {
  if (msTime > 0) LazyInstance.handler.ui.postDelayed({ func() }, msTime) else LazyInstance.handler.ui.post(func)
}

fun postWork(msTime: Long = 0, func: () -> Unit) {
  if (msTime > 0) LazyInstance.handler.work.postDelayed({ func() }, msTime) else LazyInstance.handler.work.post(func)
}