package ru.gbixahue.eu4d.android.abstraction.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.gbixahue.eu4d.core.abstracts.DataBinder

/**
 * Created by Anton Zhilenkov on 17.11.17.
 */
typealias RvCallback<D> = (Int, Int, D) -> Unit

abstract class RvBaseVH<D>(itemView: View): RecyclerView.ViewHolder(itemView), DataBinder<D>

abstract class RvVH<D: Any>(
  itemView: View,
  protected val callback: RvCallback<D>? = null
): RvBaseVH<D>(itemView) {

  protected lateinit var itemData: D

  abstract fun onDataBound(data: D)

  override fun bindData(data: D) {
    itemData = data
    onDataBound(itemData)
  }

  protected fun invokeCallback() {
    callback?.invoke(itemViewType, adapterPosition, itemData)
  }
}