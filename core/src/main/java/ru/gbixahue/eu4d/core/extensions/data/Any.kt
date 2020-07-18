package ru.gbixahue.eu4d.core.extensions.data

import java.lang.ref.Reference

/**
 * Created by Anton Zhilenkov on 18/07/2020.
 */
inline fun <T, R> Reference<T>.perform(action: (T) -> R) {
  get()?.let { action(it) }
}