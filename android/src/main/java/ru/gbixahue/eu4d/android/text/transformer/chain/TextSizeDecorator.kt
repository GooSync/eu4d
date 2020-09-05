package ru.gbixahue.eu4d.android.text.transformer.chain

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan

/**
 * Created by Anton Zhilenkov on 29.10.18.
 */
class TextSizeDecorator(
  private val sizePx: Int,
  start: Int,
  end: Int,
  source: CharSequence? = null
): BaseChainDecorator<CharSequence>(start, end, source) {

  override fun transform(): CharSequence {
    val builder = SpannableStringBuilder(chainSource)
    builder.setSpan(
      AbsoluteSizeSpan(sizePx),
      start,
      end,
      Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return builder
  }
}