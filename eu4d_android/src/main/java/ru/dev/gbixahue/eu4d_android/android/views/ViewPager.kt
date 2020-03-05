package ru.dev.gbixahue.eu4d_android.android.views

import androidx.viewpager.widget.ViewPager

/**
 * Created by Anton Zhilenkov on 24.10.18.
 */

fun ViewPager.addChangeListeners(
  stateChanged: ((Int) -> Unit)? = null,
  scrolled: ((Int, Float, Int) -> Unit)? = null,
  selected: ((Int) -> Unit)? = null
): ViewPager.OnPageChangeListener {

  val listener = object : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
      stateChanged?.invoke(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
      scrolled?.invoke(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
      selected?.invoke(position)
    }
  }
  addOnPageChangeListener(listener)
  return listener
}

fun ViewPager.removeChangeListeners(
  listener: ViewPager.OnPageChangeListener
) {
  removeOnPageChangeListener(listener)
}