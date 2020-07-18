package ru.gbixahue.eu4d.android.extension.components

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import ru.gbixahue.eu4d.android.global.singleton.threadHandler.postUI

/**
 * Created by Anton Zhilenkov on 12.09.17.
 */
fun Context.safeStartActivity(intent: Intent, failureCallback: (() -> Unit)? = null) {
  if (intent.resolveActivity(packageManager) != null) {
    postUI { ContextCompat.startActivity(this, intent, null) }
  } else {
    failureCallback?.invoke()
  }
}

fun Context.closeKeyboard() {
  val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  if (this !is Activity) return

  if (currentFocus != null) {
    imm.hideSoftInputFromWindow(currentFocus !!.windowToken, 0)
  }
}

fun Context.openKeyboard() {
  if (this !is Activity) return

  val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  inputMethodManager.toggleSoftInputFromWindow(
    window.decorView.rootView.applicationWindowToken,
    InputMethodManager.SHOW_FORCED,
    0
  )
}

fun Context.getDisplayDensity(): Float {
  val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
  return DisplayMetrics().apply {
    display.getMetrics(this)
  }.density
}

fun Context.getDisplayResolutionPx(): Point {
  val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
  return Point().apply { display.getSize(this) }
}


fun Context.openInPlayMarket(appId: String, failureCallback: (() -> Unit)? = null) {
  val marketPage = Uri.parse("market://details?id=$appId")
  val intent = Intent(Intent.ACTION_VIEW, marketPage)
  intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
  safeStartActivity(intent, failureCallback)
}

fun Context.openWebView(url: String, failureCallback: (() -> Unit)? = null) {
  try {
    val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
    applicationContext.safeStartActivity(myIntent)
  } catch (e: ActivityNotFoundException) {
    failureCallback?.invoke()
  }
}

fun Context.shareText(text: String) {
  val sendIntent: Intent = Intent().apply {
    action = Intent.ACTION_SEND
    putExtra(Intent.EXTRA_TEXT, text)
    type = "text/plain"
  }
  val shareIntent = Intent.createChooser(sendIntent, null)
  startActivity(shareIntent)
}


