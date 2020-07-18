package ru.gbixahue.eu4d.core.mech.inspectorWorker

/**
 * Created by Anton Zhilenkov on 04.03.2020.
 */
typealias ResumeWorker = () -> Unit

typealias ErrorType = Any
typealias OnWorkerError = (ErrorType, Any?) -> Any?

interface ErrorCallbackHolder {
  var onError: OnWorkerError?
}

interface Workable {
  fun doWork()
  fun done()
}

interface Worker: Workable,
  ErrorCallbackHolder {
  fun isReady(): Boolean
  fun setInspector(inspector: Inspector)
}

abstract class BaseWorker: Worker {
  override var onError: OnWorkerError? = null

  private var workerIsDone: Boolean = false
  private var workInspector: Inspector? = null

  override fun done() {
    workerIsDone = true
    workInspector?.done()
  }

  override fun isReady(): Boolean = workerIsDone

  override fun setInspector(inspector: Inspector) {
    workInspector = inspector
  }
}