package net.aquadc.advancedkotlinpatterns.common.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext

class ListAdapter<T>(
        private val items: List<T>,
        private val viewTypeOf: (T) -> Int = { 0 },
        private val holderFactory: AnkoContext<ListAdapter<T>>.(viewType: Int) -> BindingViewHolder<out T>
) : RecyclerView.Adapter<BindingViewHolder<T>>() {

    private var context: AnkoContext<ListAdapter<T>>? = null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T> {
        val context = context ?: AnkoContext.Companion.createReusable(parent.context, this).also { context = it }
        @Suppress("UNCHECKED_CAST") // it's a client's responsibility to return correct viewHolder
        return context.holderFactory(viewType) as BindingViewHolder<T>
    }

    override fun getItemViewType(position: Int): Int =
            viewTypeOf(items[position])

    override fun onBindViewHolder(holder: BindingViewHolder<T>, position: Int) {
        holder.bindInternal(items[position])
    }

    override fun onViewRecycled(holder: BindingViewHolder<T>) {
        holder.onRecycleInternal()
    }

}