package ru.dev.gbixahue.eu4d.lib.android._android.views

import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.*
import ru.dev.gbixahue.eu4d.lib.android._android.components.*
import ru.dev.gbixahue.eu4d.lib.android.global.threading.postUI
import ru.dev.gbixahue.eu4d.lib.kotlin.common.weakReference

/**
 * Created by Anton Zhilenkov on 12.09.17.
 */
fun <T>View.inflate(@LayoutRes layoutId: Int, root: ViewGroup? = null, attach: Boolean = false): T {
  return LayoutInflater.from(context).inflate(layoutId, root, attach) as T
}

inline fun <reified T: View> ViewGroup.find(@IdRes viewId: Int): T = findViewById(viewId)!!

fun View.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
  context.toast(message, length)
}

fun View.toast(messageId: Int, length: Int = Toast.LENGTH_SHORT) {
  context.toast(messageId, length)
}

fun View.enable(enable: Boolean) {
  isEnabled = enable
}

fun View.disable(byMs: Long = 0) {
  isEnabled = false
  if (byMs == 0L) return

  val wView = weakReference()
  postUI(byMs) { wView.get()?.isEnabled = true }
}

fun View.show(show: Boolean, saveBounds: Boolean = false) {
  if (show && visibility == View.VISIBLE) return
  if (! show && (visibility == View.GONE || visibility == View.INVISIBLE)) return
  visibility = if (show) View.VISIBLE else if (saveBounds) View.INVISIBLE else View.GONE
}

fun View.isRtl(): Boolean = resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL

fun View.colorFrom(@ColorRes colorId: Int) = context.colorFrom(colorId)
fun View.drawableFrom(@DrawableRes drawableId: Int) = context.drawableFrom(drawableId)
fun View.stringFrom(@StringRes stringId: Int) = context.stringFrom(stringId)
fun View.dimenFrom(@DimenRes dimenId: Int) = context.dimenFrom(dimenId)

fun View.dpToPx(dp: Float): Float = context.dpToPx(dp)
fun View.pxToDp(px: Float): Float = context.pxToDp(px)

fun View.changeBackgrounColor(colorId: Int, prevColorForAnimationId: Int = - 1, duration: Long = 300) {
  val toColor = this.context.colorFrom(colorId)
  val fromColor = this.context.colorFrom(prevColorForAnimationId)
  if (fromColor == - 1) {
    setBackgroundColor(toColor)
  } else {
    val animator = ValueAnimator()
    animator.setIntValues(fromColor, toColor)
    animator.setEvaluator(InnerArbEvaluator())
    animator.duration = duration
    animator.addUpdateListener { animation ->
      setBackgroundColor(animation.animatedValue as Int)
    }
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

