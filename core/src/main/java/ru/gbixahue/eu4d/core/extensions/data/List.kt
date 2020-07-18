package ru.gbixahue.eu4d.core.extensions.data


/**
 * Created by Anton Zhilenkov on 05/03/2020.
 */
fun <T> List<T>.print(delimiter: String = ", ", wrap: Boolean = true): String {
  val builder = StringBuilder()
  forEach { builder.append(it).append(delimiter) }
  val length = builder.length
  if (length > delimiter.length) {
    builder.delete(length - delimiter.length, length)
  }
  val result = builder.toString()

  return if (wrap) "[$result]" else result
}

fun <T> List<T>.findTheBest(bestCondition: (T, T) -> T, defBest: T? = null): T? {
  if (this.isEmpty()) return defBest

  var bestItem = this[0]
  this.forEach { bestItem = bestCondition(bestItem, it) }
  return bestItem
}

fun <T> MutableList<T>.moveToEnd(predicate: (T) -> Boolean) {
  if (this.isEmpty() || this.size == 1) return

  val filteredList = this.filter { predicate(it) }
  this.removeAll(filteredList)
  this.addAll(filteredList)
}