package net.aquadc.advancedkotlinpatterns.feature.sealed.example

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.squareup.picasso.Picasso
import net.aquadc.advancedkotlinpatterns.common.recycler.BindingViewHolder
import org.jetbrains.anko.*

fun AnkoContext<*>.createStickerHolder(picasso: Picasso): BindingViewHolder<Sticker> {

    val view = imageView {
        layoutParams = RecyclerView.LayoutParams(matchParent, wrapContent).apply {
            margin = dip(8)
        }
        adjustViewBounds = true
        maxWidth = dip(256)
        maxHeight = dip(256)
    }

    return StickerHolder(view, picasso)
}

private class StickerHolder(
        private val imageView: ImageView,
        private val picasso: Picasso
) : BindingViewHolder<Sticker>(imageView) {

    override fun bind(item: Sticker) {
        picasso.load(item.imageUrl).into(imageView)
    }

    override fun onRecycle() {
        picasso.cancelRequest(imageView)
    }

}