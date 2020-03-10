package ru.dev.gbixahue.eu4d.lib.kotlin.data_structure


/**
 * Created by Anton Zhilenkov on 05/03/2020.
 */

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