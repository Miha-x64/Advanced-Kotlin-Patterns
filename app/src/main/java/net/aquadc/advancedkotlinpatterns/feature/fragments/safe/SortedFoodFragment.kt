package net.aquadc.advancedkotlinpatterns.feature.fragments.safe

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import net.aquadc.advancedkotlinpatterns.common.getEnumSet
import net.aquadc.advancedkotlinpatterns.common.putEnumSet
import net.aquadc.advancedkotlinpatterns.common.recycler.ListAdapter
import net.aquadc.advancedkotlinpatterns.recycler.*
import org.jetbrains.anko.UI
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SortedFoodFragment : Fragment {

    @Deprecated(message = "use factory instead", level = DeprecationLevel.ERROR)
    constructor()

    private constructor(args: Bundle) { super.setArguments(args) }

    @Deprecated(message = "use factory instead", level = DeprecationLevel.ERROR)
    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        val foodKinds = arguments.getEnumSet<FoodKind>(FoodKindsKey)
        val sortBy = arguments.getSerializable(SortByParameterKey) as NutritionParameter
        val desc = arguments.getBoolean(SortDescKey)

        val sorted = foodItems
                .asSequence()
                .filter { it.kind in foodKinds }
                .sortedBy(sortBy, desc)
                .toList()

        recyclerView {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(sorted) { createFoodItemHolder() }
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

    }.view

    companion object {
        private const val FoodKindsKey = "food kinds"
        private const val SortByParameterKey = "sort by"
        private const val SortDescKey = "desc"

        operator fun invoke(kinds: Set<FoodKind>, sortBy: NutritionParameter, desc: Boolean) =
                SortedFoodFragment(Bundle(2).apply {
                    putEnumSet(FoodKindsKey, kinds)
                    putSerializable(SortByParameterKey, sortBy)
                    putBoolean(SortDescKey, desc)
                })
    }

}