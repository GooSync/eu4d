package ru.gbixahue.eu4d.android.extension.components.grouped

import android.content.Context
import android.content.res.Configuration
import android.view.View

/**
 * Created by Anton Zhilenkov on 18/07/2020.
 */
fun Context.getOrientation(): Int = resources.configuration.orientation
fun Context.isPortrait(): Boolean = getOrientation() == Configuration.ORIENTATION_PORTRAIT
fun Context.isLandscape(): Boolean = getOrientation() == Configuration.ORIENTATION_LANDSCAPE
fun Context.isTablet(): Boolean = (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >=
    Configuration.SCREENLAYOUT_SIZE_LARGE

fun View.getOrientation(): Int = context.getOrientation()
fun View.isPortrait(): Boolean = context.isPortrait()
fun View.isLandscape(): Boolean = context.isLandscape()
fun View.isTablet(): Boolean = context.isTablet()

