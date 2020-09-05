package ru.gbixahue.eu4d.android.extension.components.grouped

import android.content.Context
import android.view.View

/**
 * Created by Anton Zhilenkov on 18/07/2020.
 */
fun Context.dpToPx(dp: Int): Float = dp * resources.displayMetrics.density
fun Context.pxToDp(px: Int): Float = Math.round(px / resources.displayMetrics.density).toFloat()

fun Context.dpToPx(dp: Float): Float = dp * resources.displayMetrics.density
fun Context.pxToDp(px: Float): Float = Math.round(px / resources.displayMetrics.density).toFloat()

fun View.dpToPx(dp: Int): Float = context.dpToPx(dp)
fun View.pxToDp(px: Int): Float = context.pxToDp(px)

fun View.dpToPx(dp: Float): Float = context.dpToPx(dp)
fun View.pxToDp(px: Float): Float = context.pxToDp(px)