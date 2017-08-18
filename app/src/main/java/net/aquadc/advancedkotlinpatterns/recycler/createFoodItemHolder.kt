package net.aquadc.advancedkotlinpatterns.recycler

import android.app.Fragment
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import net.aquadc.advancedkotlinpatterns.app
import org.jetbrains.anko.*

fun Fragment.createFoodItemHolder(): FoodItemHolder {
    var photoView: ImageView? = null
    var titleView: TextView? = null
    var descriptionView: TextView? = null
    var nutritionInfoView: TextView? = null

    val itemView = UI {
        relativeLayout {
            layoutParams = RecyclerView.LayoutParams(matchParent, wrapContent)

            photoView = imageView {
                id = 1
            }.lparams(dip(150), dip(150))

            titleView = textView {
                id = 2
                textSize = 20f
                textColor = 0xFF000000.toInt()
                horizontalPadding = dip(8)
            }.lparams {
                rightOf(1)
            }

            descriptionView = textView {
                id = 3
                textSize = 16f
                horizontalPadding = dip(8)
                bottomPadding = dip(4)
            }.lparams {
                rightOf(1)
                below(2)
            }

            nutritionInfoView = textView {
                textSize = 14f
                horizontalPadding = dip(8)
                bottomPadding = dip(8)
            }.lparams {
                rightOf(1)
                below(3)
            }
        }
    }.view

    // https://meoyawn.tumblr.com/post/154221726637/anko-viewholders-and-applicative-lifting

    return FoodItemHolder(itemView, photoView!!, titleView!!, descriptionView!!, nutritionInfoView!!, app.picasso)
}