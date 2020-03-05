package ru.dev.gbixahue.eu4d_kotlin.common

/**
 * Created by Anton Zhilenkov on 05/03/2020.
 */
interface Destroyable {
  fun destroy()
}

interface DestroyableHolder: Destroyable {
  fun addToDestroyable(any: Any)
}

// Switch to Mixin by kotlin delegates
class HowToUse: DestroyableHolder {
  private val list: MutableList<Destroyable> = mutableListOf()

  override fun addToDestroyable(any: Any) {
    if (any is Destroyable) list.add(any)
  }

  override fun destroy() {
    list.forEach { it.destroy() }
  }
}