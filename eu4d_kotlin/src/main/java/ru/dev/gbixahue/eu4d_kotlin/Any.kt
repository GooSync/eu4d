package ru.dev.gbixahue.eu4d_kotlin

import java.lang.ref.WeakReference

/**
 * Created by Anton Zhilenkov on 03.03.2020.
 */
inline fun <reified T> Any.weakReference(): WeakReference<T> = WeakReference(this as T)