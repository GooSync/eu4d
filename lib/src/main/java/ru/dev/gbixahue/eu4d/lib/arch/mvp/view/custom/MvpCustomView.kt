package ru.dev.gbixahue.eu4d.lib.arch.mvp.view.custom

import ru.dev.gbixahue.eu4d.lib.arch.mvp.view.MvpView

/**
 * Created by Anton Zhilenkov on 16.07.18.
 */
interface MvpCustomView: MvpView {

  fun onViewCreated()
  fun onStart()
  fun onResume()
  fun onPause()
  fun onStop()
  fun onDestroyView()
  fun onViewDestroyed()
  fun onDetach()
}