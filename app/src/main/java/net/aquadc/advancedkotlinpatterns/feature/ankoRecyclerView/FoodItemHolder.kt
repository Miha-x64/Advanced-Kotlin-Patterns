package net.aquadc.advancedkotlinpatterns.feature.ankoRecyclerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.aquadc.advancedkotlinpatterns.recycler.BindingViewHolder

class FoodItemHolder(
        itemView: View,
        private val photoView: ImageView,
        private val titleView: TextView,
        private val descriptionView: TextView,
        private val picasso: Picasso
) : BindingViewHolder<FoodItem>(itemView) {

    override fun bind(item: FoodItem) {
        picasso.load(item.photoUrl).fit().centerCrop().into(photoView)
        titleView.text = item.title
        descriptionView.text = item.description
    }

}