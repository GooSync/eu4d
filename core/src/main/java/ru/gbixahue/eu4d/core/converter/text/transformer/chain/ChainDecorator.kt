package ru.gbixahue.eu4d.core.converter.text.transformer.chain

import ru.gbixahue.eu4d.core.converter.text.transformer.Transformer

/**
 * Created by Anton Zhilenkov on 29.10.18.
 */
interface ChainDecorator<R>: Transformer<R> {
  fun setSource(chainSource: R)
}