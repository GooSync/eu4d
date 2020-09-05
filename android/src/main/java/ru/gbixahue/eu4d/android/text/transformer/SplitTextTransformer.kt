package ru.gbixahue.eu4d.android.text.transformer

import android.text.SpannableStringBuilder
import ru.gbixahue.eu4d.core.converter.text.transformer.Transformer

class SplitTextTransformer(
  private val first: TextTransformer,
  private val second: TextTransformer,
  private val delimiter: String,
  private val withSpaces: Boolean = true
): Transformer<CharSequence> {

  override fun transform(): CharSequence {
    val mergeWithDelimiter = if (withSpaces) " $delimiter " else delimiter
    val builder = SpannableStringBuilder()
    builder.append(first.transform())
      .append(mergeWithDelimiter)
      .append(second.transform())
    return builder
  }
}