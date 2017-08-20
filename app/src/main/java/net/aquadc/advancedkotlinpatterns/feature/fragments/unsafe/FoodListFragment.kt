package net.aquadc.advancedkotlinpatterns.feature.fragments.unsafe

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import net.aquadc.advancedkotlinpatterns.app
import net.aquadc.advancedkotlinpatterns.common.getEnumSet
import net.aquadc.advancedkotlinpatterns.common.putEnumSet
import net.aquadc.advancedkotlinpatterns.common.recycler.ListAdapter
import net.aquadc.advancedkotlinpatterns.feature.fragments.getFilteredAndSortedFoodItems
import net.aquadc.advancedkotlinpatterns.feature.fragments.getSortedByPopularityFoodItems
import net.aquadc.advancedkotlinpatterns.recycler.*
import org.jetbrains.anko.UI
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Shows a list of food.
 * Popular mode:
 *   shows a list of food, sorted by descending of popularity (most popular first)
 * FilterAndSort:
 *   shows a list of food of certain [FoodKind]s sorted by specified [NutritionParameter]
 */
class FoodListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        val data = when (arguments.getInt(ModeKey)) {
            PopularMode ->
                getSortedByPopularityFoodItems()

            FilterAndSortMode ->
                getFilteredAndSortedFoodItems(
                        kinds = arguments.getEnumSet(FoodKindsKey),
                        sortBy = arguments.getSerializable(SortByParameterKey) as NutritionParameter,
                        desc = arguments.getBoolean(SortDescKey))

            else ->
                throw IndexOutOfBoundsException()
        }

        recyclerView {
            id = 1
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(data) { createFoodItemHolder(app.picasso) }
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

    }.view

    /*
    /**
     * <p>This companion describes the most unsafe way of passing arguments:
     * all constants are public, value types are not documented at all.
     * Just create a fragment, instantiate a Bundle for arguments,
     * fill it (if you guess how) and watch it burns.</p>
     *
     * <h3>Call-site example for [PopularMode]</h3>
     * <pre>FoodListFragment().apply {
     *     arguments = Bundle(1).apply {
     *         putInt(FoodListFragment.ModeKey, FoodListFragment.PopularMode)
     *     }
     * }</pre>
     *
     * <h3>Call-site example for [FilterAndSortMode]</h3>
     * <pre>FoodListFragment().apply {
     *     arguments = Bundle(4).apply {
     *         putInt(FoodListFragment.ModeKey, FoodListFragment.FilterAndSortMode)
     *         putEnumSet(FoodListFragment.FoodKindsKey, foodChecks.filterValues { it.isChecked }.keys)
     *         putSerializable(FoodListFragment.SortByParameterKey, nutritionParameterSpinner.selectedItem as NutritionParameter)
     *         putBoolean(FoodListFragment.SortDescKey, descendingCheck.isChecked)
     *     }
     * }</pre>
     */
    companion object {
        // @formatter:off
        const val ModeKey = "mode"
        const val PopularMode = 1
        const val FilterAndSortMode = 2
            const val FoodKindsKey = "food kinds"
            const val SortByParameterKey = "sort by"
            const val SortDescKey = "desc"
        // @formatter:on
    }
    */

    /**
     * <p>This Companion describes a safer way to declare arguments contract:
     * all constants are private and used by factories,
     * and call-site just assumes that factories are valid & correct.</p>
     *
     * <h3>Call-site example for [popular] mode</h3>
     * <p>`FoodListFragment.popular()`
     * (method reference: `FoodListFragment.Companion::popular`)</p>
     *
     * <h3>Call-site example for [filterAndSort] mode</h3>
     * <pre>FoodListFragment.filterAndSort(
     *         kinds = foodChecks.filterValues { it.isChecked }.keys,
     *         sortBy = nutritionParameterSpinner.selectedItem as NutritionParameter,
     *         desc = descendingCheck.isChecked)</pre>
     */
    companion object {
        // @formatter:off
        private const val ModeKey = "mode"
        private const val PopularMode = 1
        private const val FilterAndSortMode = 2
            private const val FoodKindsKey = "food kinds"
            private const val SortByParameterKey = "sort by"
            private const val SortDescKey = "desc"
        // @formatter:on

        fun popular() = FoodListFragment().apply {
            arguments = Bundle(1).apply {
                putInt(ModeKey, PopularMode)
            }
        }

        fun filterAndSort(kinds: Set<FoodKind>, sortBy: NutritionParameter, desc: Boolean) =
                FoodListFragment().apply {
                    arguments = Bundle(2).apply {
                        putInt(ModeKey, FilterAndSortMode)
                        putEnumSet(FoodKindsKey, kinds)
                        putSerializable(SortByParameterKey, sortBy)
                        putBoolean(SortDescKey, desc)
                    }
                }
    }

}