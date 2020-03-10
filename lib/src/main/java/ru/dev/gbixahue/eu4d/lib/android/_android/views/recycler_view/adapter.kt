package ru.dev.gbixahue.eu4d.lib.android._android.views.recycler_view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dev.gbixahue.eu4d.lib.android._android.views.inflate
import ru.dev.gbixahue.eu4d.lib.kotlin.common.LayoutHolder

/**
 * Created by Anton Zhilenkov on 17.11.17.
 */
abstract class RvBaseAdapter<V: RvBaseVH<D>, D>: RecyclerView.Adapter<V>() {

  protected val itemList: MutableList<D> = mutableListOf()

  open fun setItemList(newItemList: MutableList<D>) {
    itemList.clear()
    itemList.addAll(newItemList)
  }

  open fun addItems(moreItems: MutableList<D>) {
    itemList.addAll(moreItems)
  }

  open fun clearItemList() {
    itemList.clear()
  }

  open fun getItem(index: Int): D = itemList[index]

  open fun addItem(item: D) {
    itemList.add(item)
  }

  open fun removeItem(index: Int) {
    itemList.removeAt(index)
  }

  override fun getItemCount(): Int = itemList.size

  override fun onBindViewHolder(holder: V, position: Int) {
    holder.bindData(itemList[position])
  }
}



abstract class RvAdapter<V: RvVH<D>, D: Any>(
  protected val callback: RvCallback<D>? = null
): RvBaseAdapter<V, D>(), LayoutHolder {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
    val view = parent.inflate<View>(getLayoutId(), false)
    return createViewHolder(view, callback)
  }

  abstract fun createViewHolder(view: View, listener: ((Int, Int, D) -> Unit)? = null): V
}