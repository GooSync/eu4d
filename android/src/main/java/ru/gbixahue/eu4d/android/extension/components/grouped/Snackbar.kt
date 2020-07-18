package ru.gbixahue.eu4d.android.extension.components.grouped

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Anton Zhilenkov on 18/07/2020.
 */
fun View.snackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
  Snackbar.make(this, message, duration)
}

fun View.snackbar(messageId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
  Snackbar.make(this, messageId, duration)
}