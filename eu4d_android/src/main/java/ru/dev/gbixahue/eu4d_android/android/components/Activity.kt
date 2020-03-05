package ru.dev.gbixahue.eu4d_android.android.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import ru.dev.gbixahue.eu4d_android.global.threading.postUI

/**
 * Created by Anton Zhilenkov on 12.09.17.
 */
fun Activity.safeStartActivity(intent: Intent, failureCallback: (() -> Unit)? = null) {
  if (intent.resolveActivity(packageManager) != null) {
    postUI { ContextCompat.startActivity(this, intent, null) }
  } else {
    failureCallback?.invoke()
  }
}

fun Activity.closeKeyboard() {
  val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  if (currentFocus != null) {
    imm.hideSoftInputFromWindow(currentFocus !!.windowToken, 0)
  }
}

fun Activity.openKeyboard() {
  val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  inputMethodManager.toggleSoftInputFromWindow(window.decorView.rootView.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0)
}