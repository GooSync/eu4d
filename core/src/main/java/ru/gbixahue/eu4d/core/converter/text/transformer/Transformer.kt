package ru.gbixahue.eu4d.core.converter.text.transformer

/**
 * Created by Anton Zhilenkov on 29.03.18.
 */
interface Transformer<R> {
  fun transform(): R
}