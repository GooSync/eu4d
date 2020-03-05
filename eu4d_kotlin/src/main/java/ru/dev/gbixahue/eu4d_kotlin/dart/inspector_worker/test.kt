package ru.dev.gbixahue.eu4d_kotlin.dart.inspector_worker

import java.util.*

/**
 * Created by Anton Zhilenkov on 04.03.2020.
 */
class InspectorTest {

  fun test() {
    val worker = DelayWorker()
    val inspector = SequenceInspector()
    inspector.addWorker(worker)
    inspector.onWorkerDone = {}
    inspector.onDone = {}
    inspector.onError = {type, data ->}
    inspector.doWork()
  }
}

class DelayWorker : BaseWorker() {
  override fun doWork() {
    Timer().schedule(object : TimerTask() {
      override fun run() {
        this@DelayWorker.done()
      }

    }, 500)
  }
}