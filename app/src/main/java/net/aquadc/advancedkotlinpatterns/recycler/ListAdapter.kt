package net.aquadc.advancedkotlinpatterns.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class ListAdapter<T>(
        private val items: List<T>,
        private val holderFactory: (parent: ViewGroup) -> BindingViewHolder<T>
) : RecyclerView.Adapter<BindingViewHolder<T>>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T> = holderFactory(parent)

    override fun onBindViewHolder(holder: BindingViewHolder<T>, position: Int) {
        holder.bindInternal(items[position])
    }

    override fun onViewRecycled(holder: BindingViewHolder<T>) {
        holder.onRecycleInternal()
    }

}