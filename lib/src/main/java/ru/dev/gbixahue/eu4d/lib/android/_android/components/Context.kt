package ru.dev.gbixahue.eu4d.lib.android._android.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Point
import android.net.Uri
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import ru.dev.gbixahue.eu4d.lib.android.global.threading.postUI
import java.lang.ref.WeakReference

/**
 * Created by Anton Zhilenkov on 12.09.17.
 */
fun Context.weakReference() = WeakReference<Context>(this)

fun Context.colorFrom(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)
fun Context.drawableFrom(@DrawableRes drawableId: Int) = AppCompatResources.getDrawable(this, drawableId)
fun Context.stringFrom(@StringRes stringId: Int) = resources.getString(stringId)
fun Context.dimenFrom(@DimenRes dimenId: Int) = resources.getDimension(dimenId)

fun Context.toast(messageResId: Int, length: Int = Toast.LENGTH_SHORT) {
  if (messageResId < 0) return
  toast(stringFrom(messageResId), length)
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
  Toast.makeText(this, message, length).show()
}

fun Context.dpToPx(dp: Float): Float = dp * resources.displayMetrics.density
fun Context.pxToDp(px: Float): Float = Math.round(px / resources.displayMetrics.density).toFloat()

fun Context.displayDensity(): Float {
  val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
  val metrics = DisplayMetrics()
  display.getMetrics(metrics)
  return metrics.density
}

fun Context.displayResolutionPx(): Point {
  val point = Point()
  val service = (getSystemService(Context.WINDOW_SERVICE) as? WindowManager) ?: return point

  service.defaultDisplay.getSize(point)
  return point
}

fun Context.safeStartActivity(intent: Intent, failureCallback: (() -> Unit)? = null) {
  if (intent.resolveActivity(packageManager) != null) {
    postUI { ContextCompat.startActivity(this, intent, null) }
  } else {
    failureCallback?.invoke()
  }
}

fun Context.showInPlayMarket(appId: String, failureCallback: (() -> Unit)? = null) {
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

fun Context.isPortrait(): Boolean = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
fun Context.isLandscape(): Boolean = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
fun Context.getOrientation(): Int = resources.configuration.orientation
fun Context.isTablet(): Boolean = (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >=
    Configuration.SCREENLAYOUT_SIZE_LARGE

