package net.aquadc.advancedkotlinpatterns.feature.ankoRecyclerView

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import net.aquadc.advancedkotlinpatterns.common.recycler.ListAdapter
import net.aquadc.advancedkotlinpatterns.recycler.createFoodItemHolder
import net.aquadc.advancedkotlinpatterns.recycler.foodItems
import org.jetbrains.anko.UI
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AnkoRecyclerViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        recyclerView {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(foodItems) { createFoodItemHolder() }
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

    }.view

}