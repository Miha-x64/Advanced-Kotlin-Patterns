package net.aquadc.advancedkotlinpatterns.feature.sealed.example

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.aquadc.advancedkotlinpatterns.common.recycler.BindingViewHolder
import org.jetbrains.anko.*

fun AnkoContext<*>.createPhotoHolder(picasso: Picasso): BindingViewHolder<Photo> {

    var imageView: ImageView? = null
    var textView: TextView? = null

    frameLayout {
        layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent).apply {
            margin = dip(8)
        }

        imageView = imageView {
            adjustViewBounds = true
        }.lparams(width = matchParent)

        textView = textView {
            padding = dip(8)
            backgroundColor = 0x88_FFFFFF.toInt()
        }.lparams(width = matchParent, gravity = Gravity.BOTTOM)
    }

    return PhotoHolder(view, imageView!!, textView!!, picasso)
}

private class PhotoHolder(
        itemView: View,
        private val imageView: ImageView,
        private val textView: TextView,
        private val picasso: Picasso
) : BindingViewHolder<Photo>(itemView) {

    override fun bind(item: Photo) {
        picasso.load(item.photoUrl).into(imageView)
        textView.text = item.text
    }

    override fun onRecycle() {
        super.onRecycle()
    }

}