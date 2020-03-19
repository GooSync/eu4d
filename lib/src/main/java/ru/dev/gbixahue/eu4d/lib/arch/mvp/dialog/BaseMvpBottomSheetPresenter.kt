package ru.dev.gbixahue.eu4d.lib.arch.mvp.dialog

import ru.dev.gbixahue.eu4d.lib.android.OnBackPressHandler
import ru.dev.gbixahue.eu4d.lib.android.global.log.Log
import ru.dev.gbixahue.eu4d.lib.kotlin.common.Destroyable


/**
 * Created by Anton Zhilenkov on 21.08.18.
 */
open class BaseMvpBottomSheetPresenter<View: MvpDlgView>: MvpDlgPresenter<View>,
    OnBackPressHandler {

  protected var view: View? = null

  protected val stopDisposable = mutableListOf<Destroyable>()
  protected val dismissDisposable = mutableListOf<Destroyable>()
  protected val cancelDisposable = mutableListOf<Destroyable>()

  override fun attached(view: View) {
    Log.d(this, "mvpView attached")
    this.view = view
  }

  override fun contentViewSet() {
    Log.d(this, "contentViewSet")
  }

  override fun created() {
    Log.d(this, "created")
  }

  override fun started() {
    Log.d(this, "started")
  }

  override fun showed() {
    Log.d(this, "showed")
  }

  override fun stopped() {
    Log.d(this, "stoped")
    stopDisposable.forEach { it.destroy() }
  }

  override fun dismissed() {
    Log.d(this, "dismissed")
    dismissDisposable.forEach { it.destroy() }
  }

  override fun cancelled() {
    Log.d(this, "cancelled")
    cancelDisposable.forEach { it.destroy() }
  }

  override fun detached() {
    Log.d(this, "detached")
    view = null
  }

  override fun onBackPressed(): Boolean {
    Log.d(this, "onBackPressed")
    return true
  }
}