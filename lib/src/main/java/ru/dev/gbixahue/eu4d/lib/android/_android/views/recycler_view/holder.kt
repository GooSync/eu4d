package ru.dev.gbixahue.eu4d.lib.android._android.views.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.dev.gbixahue.eu4d.lib.kotlin.common.DataBinder

/**
 * Created by Anton Zhilenkov on 17.11.17.
 */
abstract class RvBaseVH<D>(itemView: View): RecyclerView.ViewHolder(itemView), DataBinder<D>



abstract class RvVH<D: Any>(
    itemView: View,
    protected val callback: RvCallback<D>? = null
): RvBaseVH<D>(itemView), View.OnClickListener {

  protected lateinit var itemData: D

  init {
    setOnClickListenerToView(itemView)
  }

  protected fun setOnClickListenerToView(view: View) {
    view.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    callback?.invoke(itemViewType, adapterPosition, itemData)
  }

  override fun bindData(data: D) {
    itemData = data
  }
}