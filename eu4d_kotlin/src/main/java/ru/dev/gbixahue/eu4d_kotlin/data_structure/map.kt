package ru.dev.gbixahue.eu4d_kotlin.data_structure

/**
 * Created by Anton Zhilenkov on 26.10.17.
 */
fun <K, V> Map<K, V>.print(tag: String = "Map", printer: (String) -> Unit) {
  this.forEach { printer("${it.key}=${it.value}") }
}

fun <K, V> Map<K, V>.stringOf(nameOf: String = ""): String {
  if (this.isEmpty()) return ""
  val sBuilder = StringBuilder(if (nameOf.isEmpty()) "[ " else "$nameOf [ ")
  this.forEach { sBuilder.append("${it.key}=${it.value}").append(", ") }
  sBuilder.delete(sBuilder.length - 2, sBuilder.length).append(" ]")
  return sBuilder.toString()
}