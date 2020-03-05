package ru.dev.gbixahue.eu4d_android.android.views

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Anton Zhilenkov on 03.03.2020.
 */
fun EditText.text() = text.toString()

fun EditText.moveCursorToStart() {
  moveCursorTo(0)
}

fun EditText.moveCursorToEnd() {
  moveCursorTo(text.length)
}

fun EditText.moveCursorTo(position: Int) {
  when {
    position < 0 -> setSelection(0)
    position > text.length -> setSelection(text.length)
    else -> setSelection(position)
  }
}

fun EditText.focusAndMoveCursorToEnd() {
  if (! isFocused) requestFocus()
  if (text.isEmpty()) return

  moveCursorTo(text.length)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit): TextWatcher {
  val watcher = object: TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(editable: Editable?) {
      afterTextChanged.invoke(editable.toString())
    }
  }
  this.addTextChangedListener(watcher)
  return watcher
}

fun EditText.addInputFilter(filter: InputFilter) {
  val inputFilters = mutableListOf<InputFilter>()
  filters.forEach { inputFilters.add(it) }
  inputFilters.add(filter)
  if (inputFilters.isNotEmpty()) filters = inputFilters.toTypedArray()
}

fun EditText.setOnImeActionListener(action: Int, handler: () -> Unit) {
  this.setOnEditorActionListener { view, actionId, event ->
    if (actionId == action) {
      handler.invoke()
      return@setOnEditorActionListener true
    }
    return@setOnEditorActionListener false
  }
}