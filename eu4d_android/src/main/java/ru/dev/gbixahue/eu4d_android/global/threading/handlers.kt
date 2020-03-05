package ru.dev.gbixahue.eu4d_android.global.threading

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

/**
 * Created by Anton Zhilenkov on 20.08.18.
 */

object Threading {
  val uiHandler: Handler = Handler(Looper.getMainLooper())
  val workHandler: Handler = Handler(HandlerThread("Application worker thread").apply { start() }.looper)

  fun postInCurrentThread(msTime: Long, func: () -> Unit) {
    Handler().postDelayed({ func() }, msTime)
  }
}


fun post(msTime: Long, func: () -> Unit) {
  Threading.postInCurrentThread(msTime, func)
}

fun postUI(msTime: Long = 0, func: () -> Unit) {
  if (msTime > 0) Threading.uiHandler.postDelayed({ func() }, msTime) else Threading.uiHandler.post(func)
}

fun postWork(msTime: Long = 0, func: () -> Unit) {
  if (msTime > 0) Threading.workHandler.postDelayed({ func() }, msTime) else Threading.workHandler.post(func)
}