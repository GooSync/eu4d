package ru.dev.gbixahue.eu4d_kotlin.common

/**
 * Created by Anton Zhilenkov on 04.03.2020.
 */
interface Typed<T> {
  fun getType(): T
}

interface SubTyped<T> {
  fun getSubType(): T
}

interface Provider<T> {
  fun get(): T
}

interface Loader<T> {
  fun <T> load(): T
}

interface Filter<T> {
  fun filter(source: T): T
}

interface Holder<T> {
  fun get(): T
}

interface TypedHolder<T, K> {
  fun register(type: T, value: K)

  fun get(type: T): K?
}

class BaseTypedHolder<T, K>: TypedHolder<T, K> {
  private val holder = mutableMapOf<T, K>()

  override fun register(type: T, value: K) {
    holder[type] = value
  }

  override fun get(type: T): K? = holder[type]
}

interface Converter<A, B> {
  fun convert(from: A): B
}

interface IBuilder<A, B> {
  fun build(from: A): B
}

interface ModelBuilder<F, T>: IBuilder<F, T> {
  fun canBuild(from: F): Boolean
}