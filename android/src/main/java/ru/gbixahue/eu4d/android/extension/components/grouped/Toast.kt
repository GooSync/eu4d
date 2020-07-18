package ru.gbixahue.eu4d.android.extension.components.grouped

import android.content.Context
import android.view.View
import android.widget.Toast
import ru.gbixahue.eu4d.android.extension.components.stringFrom

/**
 * Created by Anton Zhilenkov on 18/07/2020.
 */
fun Context.toast(messageResId: Int, length: Int = Toast.LENGTH_SHORT) {
  if (messageResId < 0) return
  toast(stringFrom(messageResId), length)
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
  Toast.makeText(this, message, length).show()
}

fun View.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
  context.toast(message, length)
}

fun View.toast(messageId: Int, length: Int = Toast.LENGTH_SHORT) {
  context.toast(messageId, length)
}