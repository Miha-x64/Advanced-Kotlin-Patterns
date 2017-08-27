package net.aquadc.advancedkotlinpatterns.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.aquadc.advancedkotlinpatterns.common.recycler.BindingViewHolder

class FoodItemHolder(
        itemView: View,
        private val photoView: ImageView,
        private val titleView: TextView,
        private val descriptionView: TextView,
        private val nutritionInfoView: TextView,
        private val picasso: Picasso
) : BindingViewHolder<FoodItem>(itemView) {

    override fun bind(item: FoodItem) {
        picasso.load(item.photoUrl).fit().centerCrop().into(photoView)
        titleView.text = item.name
        descriptionView.text = item.description
        nutritionInfoView.text = item.nutritionInfo.toString()
    }

    override fun onRecycle() {
        picasso.cancelRequest(photoView)
    }

}