package ru.dev.gbixahue.eu4d.lib.android._android.views

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.*
import ru.dev.gbixahue.eu4d.lib.android._android.components.*

/**
 * Created by Anton Zhilenkov on 12.09.17.
 */
fun <T> ViewGroup.inflate(@LayoutRes resId: Int, attach: Boolean = false): T =
    LayoutInflater.from(context).inflate(resId, this, attach) as T

inline fun <reified T> ViewGroup.find(@IdRes viewId: Int): T = findViewById(viewId)!!

fun ViewGroup.enableLayoutAnimation() {
  layoutTransition = LayoutTransition()
  layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
}

fun ViewGroup.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
  context.toast(message, length)
}

fun ViewGroup.toast(messageId: Int, length: Int = Toast.LENGTH_SHORT) {
  context.toast(messageId, length)
}

fun ViewGroup.colorFrom(@ColorRes colorId: Int) = context.colorFrom(colorId)
fun ViewGroup.drawableFrom(@DrawableRes drawableId: Int) = context.drawableFrom(drawableId)
fun ViewGroup.stringFrom(@StringRes stringId: Int) = context.stringFrom(stringId)
fun ViewGroup.dimenFrom(@DimenRes dimenId: Int) = context.dimenFrom(dimenId)