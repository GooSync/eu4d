package ru.gbixahue.eu4d.android.extension.view

import android.animation.Animator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.view.View
import androidx.annotation.IdRes
import ru.gbixahue.eu4d.android.extension.components.grouped.colorFrom
import ru.gbixahue.eu4d.android.global.singleton.threadHandler.postUI
import ru.gbixahue.eu4d.core.abstracts.weakReference

/**
 * Created by Anton Zhilenkov on 12.09.17.
 */
inline fun <reified T: View> View.find(@IdRes viewId: Int): T = findViewById(viewId)

fun View.enable(enable: Boolean) {
  isEnabled = enable
}

fun View.disableWithTimeout(milliseconds: Long = 0) {
  if (milliseconds == 0L) return

  isEnabled = false
  val wView = weakReference()
  postUI(milliseconds) { wView.get()?.isEnabled = true }
}

fun View.show(show: Boolean, saveBounds: Boolean = false) {
  if (show && visibility == View.VISIBLE) return
  if (! show && (visibility == View.GONE || visibility == View.INVISIBLE)) return
  visibility = if (show) View.VISIBLE else if (saveBounds) View.INVISIBLE else View.GONE
}

fun View.isRtl(): Boolean = resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL


typealias AnimationCallback = (Animator) -> Unit

fun View.animateBackgroundColorChanges(
  toColorId: Int,
  fromColorId: Int = - 1,
  duration: Long = 300,
  onStart: AnimationCallback? = null,
  onEnd: AnimationCallback? = null,
  onRepeat: AnimationCallback? = null,
  onCancel: AnimationCallback? = null
) {
  val toColor = this.colorFrom(toColorId)
  val fromColor = this.colorFrom(fromColorId)
  if (fromColor == - 1) {
    setBackgroundColor(toColor)
  } else {
    val animator = ValueAnimator()
    animator.setIntValues(fromColor, toColor)
    animator.setEvaluator(InnerArbEvaluator())
    animator.addListener(object: Animator.AnimatorListener {
      override fun onAnimationStart(animation: Animator) {
        onStart?.invoke(animation)
      }

      override fun onAnimationEnd(animation: Animator) {
        onEnd?.invoke(animation)
      }

      override fun onAnimationRepeat(animation: Animator) {
        onRepeat?.invoke(animation)
      }

      override fun onAnimationCancel(animation: Animator) {
        onCancel?.invoke(animation)
      }
    })
    animator.duration = duration
    animator.addUpdateListener { animation -> setBackgroundColor(animation.animatedValue as Int) }
    animator.start()
  }
}

class InnerArbEvaluator: TypeEvaluator<Int> {

  override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
    val startA = startValue shr 24 and 0xff
    val startR = startValue shr 16 and 0xff
    val startG = startValue shr 8 and 0xff
    val startB = startValue and 0xff

    val endA = endValue shr 24 and 0xff
    val endR = endValue shr 16 and 0xff
    val endG = endValue shr 8 and 0xff
    val endB = endValue and 0xff

    return ((startA + (fraction * (endA - startA)).toInt() shl 24)
        or (startR + (fraction * (endR - startR)).toInt() shl 16)
        or (startG + (fraction * (endG - startG)).toInt() shl 8)
        or (startB + (fraction * (endB - startB)).toInt()))
  }
}

