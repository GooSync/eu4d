package ru.dev.gbixahue.eu4d_android.android.views

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.widget.TextView
import ru.dev.gbixahue.eu4d_android.android.components.drawableFrom
import java.util.*

/**
 * Created by Anton Zhilenkov on 12.09.17.
 */
fun TextView.text() = toString()

fun TextView.caps(locale: Locale? = null) {
  val textValue = text()
  text = if (locale == null) textValue.toUpperCase() else textValue.toUpperCase(locale)
}

fun TextView.underline() {
  paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun TextView.capsAndUnderline(locale: Locale? = null) {
  caps(locale)
  underline()
}

fun TextView.fromHtml(htmlString: String) {
  text = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) Html.fromHtml(htmlString) else Html.fromHtml(htmlString, 0)
}

fun TextView.markInvalid(invalid: Boolean, drawableId: Int) {
  setDrawable(if (invalid) this.context.drawableFrom(drawableId) else null)
}

fun TextView.setDrawable(drawable: Drawable?, start: Int? = null, top: Int? = null, end: Int? = null, bottom: Int? = null) {
  drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
  start?.let { setCompoundDrawablesRelative(drawable, null, null, null) }
  top?.let { setCompoundDrawablesRelative(null, drawable, null, null) }
  end?.let { setCompoundDrawablesRelative(null, null, drawable, null) }
  bottom?.let { setCompoundDrawablesRelative(null, null, null, drawable) }
}