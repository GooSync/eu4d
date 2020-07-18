package ru.gbixahue.eu4d.core.mech.inspectorWorker

import java.util.*

/**
 * Created by Anton Zhilenkov on 04.03.2020.
 */
interface Inspector: Workable, ErrorCallbackHolder {
  fun addWorker(worker: Worker)

  fun resume()

  fun getWorkerCount(): Int
}

abstract class BaseInspector: Inspector {

  override var onError: OnWorkerError? = null

  var onDone: (() -> Unit)? = null
  var onWorkerDone: ((Any) -> Unit)? = null

  protected val workerList = mutableListOf<Worker>()
  protected var countOfDoneWorkers = 0
  protected var workerWithError: Worker? = null

  private var hasError = false

  override fun addWorker(worker: Worker) {
    worker.setInspector(this);

    worker.onError = { error, data ->
      workerWithError = worker
      this.onError?.invoke(error, data)
    }
    workerList.add(worker);
  }

  override fun resume() {
    if (workerWithError == null) return

    val worker = workerWithError !!
    workerWithError = null
    hasError = false
    worker.doWork()
  }

  override fun done() {
    if (hasError) return

    notifyDoneWork()
    if (workerList.all { it.isReady() }) {
      onDone?.invoke()
    }
  }

  override fun getWorkerCount(): Int = workerList.size

  protected open fun notifyDoneWork() {
    countOfDoneWorkers ++
    onWorkerDone?.invoke(countOfDoneWorkers)
  }

}

class SequenceInspector: BaseInspector() {
  private val queue: Queue<Worker> = LinkedList()

  override fun doWork() {
    queue.addAll(workerList)
    toWork(queue.poll());
  }

  override fun done() {
    notifyDoneWork()
    if (queue.isEmpty()) {
      onDone?.invoke()
      return
    }
    toWork(queue.poll());
  }

  private fun toWork(worker: Worker?) {
    if (worker == null) return

    try {
      worker.doWork()
    } catch (e: Exception) {
      worker.onError?.invoke("Exception", e)
    }
  }
}