package ru.dev.gbixahue.eu4d_kotlin

import java.lang.ref.Reference

/**
 * Created by Anton Zhilenkov on 24.10.18.
 */

inline fun <T, R> Reference<T>.perform(action: (T) -> R) {
  get()?.let { action(it) }
}