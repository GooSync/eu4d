package ru.dev.gbixahue.eu4d.lib.kotlin.text.transformer.text.chain

import ru.dev.gbixahue.eu4d.lib.kotlin.text.transformer.Transformer

/**
 * Created by Anton Zhilenkov on 29.10.18.
 */
interface ChainDecorator<R>: Transformer<R> {
  fun setSource(chainSource: R)
}