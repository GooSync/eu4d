package ru.dev.gbixahue.eu4d.lib.arch.mvp.presenter

import ru.dev.gbixahue.eu4d.lib.android.OnBackPressHandler
import ru.dev.gbixahue.eu4d.lib.android.global.log.Log
import ru.dev.gbixahue.eu4d.lib.arch.mvp.view.MvpView
import ru.dev.gbixahue.eu4d.lib.kotlin.common.Destroyable

/**
 * Created by Anton Zhilenkov on 03.07.17.
 */

abstract class BaseMvpPresenter<View: MvpView>: MvpPresenter<View>,
    OnBackPressHandler, DisposableHolder {

  protected var view: View? = null

  protected val pauseDisposable = mutableListOf<Destroyable>()
  protected val stopDisposable = mutableListOf<Destroyable>()
  protected val destroyDisposable = mutableListOf<Destroyable>()

  override fun attached(view: View) {
    Log.d(this, "attached")
    this.view = view
  }

  override fun created() {
    Log.d(this, "created")
  }

  override fun createView() {
    Log.d(this, "createView")
  }

  override fun viewCreated() {
    Log.d(this, "viewCreated")
  }

  override fun started() {
    Log.d(this, "started")
  }

  override fun resumed() {
    Log.d(this, "resumed")
  }

  override fun paused() {
    Log.d(this, "paused")
    pauseDisposable.forEach { it.destroy() }
  }

  override fun stopped() {
    Log.d(this, "stopped")
    stopDisposable.forEach { it.destroy() }
  }

  override fun destroyView() {
    Log.d(this, "destroyView")
    destroyDisposable.forEach { it.destroy() }
  }

  override fun viewDestroyed() {
    Log.d(this, "viewDestroyed")
  }

  override fun detached() {
    Log.d(this, "detached")
    this.view = null
  }

  override fun disposeAll() {
    pauseDisposable.forEach { it.destroy() }
    stopDisposable.forEach { it.destroy() }
    destroyDisposable.forEach { it.destroy() }
  }

  override fun onBackPressed(): Boolean = true
}