package net.aquadc.advancedkotlinpatterns.feature.sealed.example

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.aquadc.advancedkotlinpatterns.common.recycler.BindingViewHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

fun AnkoContext<*>.createLinkHolder(picasso: Picasso): BindingViewHolder<Link> {

    var imageView: ImageView? = null
    var titleTextView: TextView? = null
    var descriptionTextView: TextView? = null

    cardView {
        layoutParams = RecyclerView.LayoutParams(matchParent, wrapContent).apply {
            margin = dip(8)
        }
        cardElevation = dip(4).toFloat()
        radius = dip(8).toFloat()

        imageView = imageView {
            layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent)
            adjustViewBounds = true
            maxHeight = dip(256)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        verticalLayout {
            layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent, Gravity.BOTTOM)
            backgroundColor = 0x88_FFFFFF.toInt()
            padding = dip(8)

            titleTextView = textView {
                textSize = 16f
                textColor = Color.BLACK
            }.lparams(width = matchParent)

            descriptionTextView = textView {
            }.lparams(width = matchParent)
        }
    }

    return LinkHolder(view, imageView!!, titleTextView!!, descriptionTextView!!, picasso)
}

private class LinkHolder(
        itemView: View,
        private val imageView: ImageView,
        private val titleTextView: TextView,
        private val descriptionTextView: TextView,
        private val picasso: Picasso
) : BindingViewHolder<Link>(itemView) {

    private var currentItem: Link? = null

    init {
        itemView.setOnClickListener {
            it.context.browse(currentItem!!.url)
        }
    }

    override fun bind(item: Link) {
        currentItem = item
        picasso.load(item.imageUrl).into(imageView)
        titleTextView.text = item.title
        descriptionTextView.text = item.description
    }

    override fun onRecycle() {
        currentItem = null
        picasso.cancelRequest(imageView)
    }

}
