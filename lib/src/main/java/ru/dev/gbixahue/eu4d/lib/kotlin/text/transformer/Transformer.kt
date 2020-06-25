package ru.dev.gbixahue.eu4d.lib.kotlin.text.transformer

/**
 * Created by Anton Zhilenkov on 29.03.18.
 */
interface Transformer<R> {
  fun transform(): R
}