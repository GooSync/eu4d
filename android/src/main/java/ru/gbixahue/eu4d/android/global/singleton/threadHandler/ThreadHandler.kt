package ru.gbixahue.eu4d.android.global.singleton.threadHandler

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

/**
 * Created by Anton Zhilenkov on 20.08.18.
 */

class ThreadHandler {
  val ui: Handler = Handler(Looper.getMainLooper())
  val background: Handler = Handler(HandlerThread("Application worker thread").apply { start() }.looper)

  fun postInCurrentThread(msTime: Long, func: () -> Unit) {
    post(Handler(), msTime, func)
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
  post(LazyInstance.handler.ui, msTime, func)
}

fun postWork(msTime: Long = 0, func: () -> Unit) {
  post(LazyInstance.handler.background, msTime, func)
}

internal fun post(handler: Handler, msTime: Long, func: () -> Unit) {
  when {
    msTime <= 0 -> handler.post { func() }
    msTime > 0 -> handler.postDelayed({ func() }, msTime)
  }
}