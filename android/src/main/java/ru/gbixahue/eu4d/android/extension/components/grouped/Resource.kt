package ru.gbixahue.eu4d.android.extension.components.grouped

import android.content.Context
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

/**
 * Created by Anton Zhilenkov on 18/07/2020.
 */
fun Context.colorFrom(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)
fun Context.drawableFrom(@DrawableRes drawableId: Int) = AppCompatResources.getDrawable(this, drawableId)
fun Context.stringFrom(@StringRes stringId: Int) = resources.getString(stringId)
fun Context.dimenFrom(@DimenRes dimenId: Int) = resources.getDimension(dimenId)


fun View.colorFrom(@ColorRes colorId: Int) = context.colorFrom(colorId)
fun View.drawableFrom(@DrawableRes drawableId: Int) = context.drawableFrom(drawableId)
fun View.stringFrom(@StringRes stringId: Int) = context.stringFrom(stringId)
fun View.dimenFrom(@DimenRes dimenId: Int) = context.dimenFrom(dimenId)
