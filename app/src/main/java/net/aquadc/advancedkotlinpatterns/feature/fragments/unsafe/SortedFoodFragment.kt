package net.aquadc.advancedkotlinpatterns.feature.fragments.unsafe

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import net.aquadc.advancedkotlinpatterns.common.recycler.ListAdapter
import net.aquadc.advancedkotlinpatterns.recycler.NutritionParameter
import net.aquadc.advancedkotlinpatterns.recycler.createFoodItemHolder
import net.aquadc.advancedkotlinpatterns.recycler.foodItems
import net.aquadc.advancedkotlinpatterns.recycler.getParameter
import org.jetbrains.anko.UI
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SortedFoodFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        val parameter = arguments.getSerializable(NutritionParamKey) as NutritionParameter
        val descending = arguments.getBoolean(SortDescKey)

        val sorted = foodItems.asSequence()
                .let {
                    if (descending) it.sortedByDescending { it.nutritionInfo.getParameter(parameter) }
                    else it.sortedBy { it.nutritionInfo.getParameter(parameter) }
                }
                .toList()

        recyclerView {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(sorted) { createFoodItemHolder() }
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

    }.view

    companion object {
        const val NutritionParamKey = "nutrition param"
        const val SortDescKey = "sort desc"
    }

}