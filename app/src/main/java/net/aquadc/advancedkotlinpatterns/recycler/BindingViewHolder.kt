package net.aquadc.advancedkotlinpatterns.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BindingViewHolder<T>(
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    protected var item: T? = null
        private set

    fun bindInternal(item: T) {
        this.item = item
        bind(item)
    }

    fun onRecycleInternal() {
        item = null
        onRecycle()
    }

    abstract fun bind(item: T)
    open fun onRecycle() = Unit

}