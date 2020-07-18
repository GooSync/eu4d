package ru.gbixahue.eu4d.android.extension.components.grouped

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by Anton Zhilenkov on 18/07/2020.
 */
fun Context.copyToClipboard(value: Any, label: String = "") {
  val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return

  val clip: ClipData = ClipData.newPlainText(label, value.toString())
  clipboard.setPrimaryClip(clip)
}

fun View.copyToClipboard(value: Any, label: String = "") {
  context.copyToClipboard(value, label)
}

fun TextView.copyToClipboard(label: String = "") {
  context.copyToClipboard(this.text, label)
}