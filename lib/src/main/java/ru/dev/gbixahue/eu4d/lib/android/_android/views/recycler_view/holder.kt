package ru.dev.gbixahue.eu4d.lib.android._android.views.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.dev.gbixahue.eu4d.lib.kotlin.common.DataBinder

/**
 * Created by Anton Zhilenkov on 17.11.17.
 */
abstract class RvBaseVH<D>(itemView: View): RecyclerView.ViewHolder(itemView), DataBinder<D> {
  abstract fun onDataBound(data: D)
}



abstract class RvVH<D: Any>(
    itemView: View,
    protected val callback: RvCallback<D>? = null
): RvBaseVH<D>(itemView) {

  protected lateinit var itemData: D

  override fun bindData(data: D) {
    itemData = data
    onDataBound(itemData)
  }

  protected fun invokeCallback() {
    callback?.invoke(itemViewType, adapterPosition, itemData)
  }
}