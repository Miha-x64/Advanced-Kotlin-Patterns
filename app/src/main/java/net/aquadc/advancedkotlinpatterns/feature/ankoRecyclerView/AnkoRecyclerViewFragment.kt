package net.aquadc.advancedkotlinpatterns.feature.ankoRecyclerView

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import net.aquadc.advancedkotlinpatterns.app
import net.aquadc.advancedkotlinpatterns.recycler.ListAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AnkoRecyclerViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        recyclerView {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(foodItems) {

                var photoView: ImageView? = null
                var titleView: TextView? = null
                var descriptionView: TextView? = null

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
                            textSize = 16f
                            horizontalPadding = dip(8)
                            bottomPadding = dip(8)
                        }.lparams {
                            rightOf(1)
                            below(2)
                        }
                    }
                }.view

                FoodItemHolder(itemView, photoView!!, titleView!!, descriptionView!!, app.picasso)

                // https://meoyawn.tumblr.com/post/154221726637/anko-viewholders-and-applicative-lifting
            }
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

    }.view

}