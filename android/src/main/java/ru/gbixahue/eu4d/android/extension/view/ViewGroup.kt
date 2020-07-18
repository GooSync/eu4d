package ru.gbixahue.eu4d.android.extension.view

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.transition.AutoTransition
import androidx.transition.Transition
import androidx.transition.TransitionManager

/**
 * Created by Anton Zhilenkov on 12.09.17.
 */
fun <T> ViewGroup.inflate(@LayoutRes resId: Int, attach: Boolean = false): T =
  LayoutInflater.from(context).inflate(resId, this, attach) as T

fun ViewGroup.enableLayoutAnimation(transitionType: Int = LayoutTransition.CHANGING) {
  layoutTransition = LayoutTransition()
  layoutTransition.enableTransitionType(transitionType)
}

fun ViewGroup.beginDelayedTransition(transition: Transition = AutoTransition()) {
  TransitionManager.beginDelayedTransition(this, transition)
}