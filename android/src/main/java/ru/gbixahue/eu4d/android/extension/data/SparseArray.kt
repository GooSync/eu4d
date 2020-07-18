package ru.gbixahue.eu4d.android.extension.data

import android.util.SparseArray
import android.util.SparseBooleanArray
import android.util.SparseIntArray
import java.util.*

/**
 * Created by Anton Zhilenkov on 22.09.17.
 */

inline fun <E> SparseArray<E>.forEach(action: (Int, E) -> Unit) {
  val size = this.size()
  for (i in 0 until size) {
    if (size != this.size()) throw ConcurrentModificationException()
    action(this.keyAt(i), this.valueAt(i))
  }
}

/**
 *  Iterate the receiver [SparseBooleanArray]
 *  @action an action to invoke on each key value pair
 *  @throws [ConcurrentModificationException] if modified while iterating
 */
inline fun SparseBooleanArray.forEach(action: (Int, Boolean) -> Unit) {
  val size = this.size()
  for (i in 0 until size) {
    if (size != this.size()) throw ConcurrentModificationException()
    action(this.keyAt(i), this.valueAt(i))
  }
}

/**
 *  Iterate the receiver [SparseIntArray]
 *  @action an action to invoke on each key value pair
 *  @throws [ConcurrentModificationException] if modified while iterating
 */
inline fun SparseIntArray.forEach(action: (Int, Int) -> Unit) {
  val size = this.size()
  for (i in 0 until size) {
    if (size != this.size()) throw ConcurrentModificationException()
    action(this.keyAt(i), this.valueAt(i))
  }
}
