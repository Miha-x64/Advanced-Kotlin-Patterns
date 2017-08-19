package net.aquadc.advancedkotlinpatterns.feature.fragments.safe

import android.app.Fragment
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import net.aquadc.advancedkotlinpatterns.common.*
import net.aquadc.advancedkotlinpatterns.common.recycler.ListAdapter
import net.aquadc.advancedkotlinpatterns.feature.fragments.getFilteredAndSortedFoodItems
import net.aquadc.advancedkotlinpatterns.feature.fragments.getSortedByPopularityFoodItems
import net.aquadc.advancedkotlinpatterns.recycler.FoodItem
import net.aquadc.advancedkotlinpatterns.recycler.FoodKind
import net.aquadc.advancedkotlinpatterns.recycler.NutritionParameter
import net.aquadc.advancedkotlinpatterns.recycler.createFoodItemHolder
import org.jetbrains.anko.UI
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Shows a list of food according to the specified [Mode].
 */
class FoodListFragment : Fragment {

    @Deprecated(message = "use factory instead", level = DeprecationLevel.ERROR)
    constructor()

    private constructor(args: Bundle) { super.setArguments(args) }

    @Deprecated(message = "use factory instead", level = DeprecationLevel.ERROR)
    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        val mode = arguments.getParcelable<Mode>(ModeKey)

        recyclerView {
            id = 1
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(mode.data) { createFoodItemHolder() }
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

    }.view

    companion object {
        private const val ModeKey = "mode"

        operator fun invoke(mode: Mode) =
                FoodListFragment(Bundle(1).apply {
                    putParcelable(ModeKey, mode)
                })
    }

    interface Mode : Parcelable {
        val data: List<FoodItem>

        /**
         * Gives a list of food, sorted by descending of popularity (most popular first)
         */
        object Popular : Mode, BoringParcelable {
            override val data: List<FoodItem> get() = getSortedByPopularityFoodItems()

            override fun writeToParcel(dest: Parcel, flags: Int) = Unit
            @JvmField val CREATOR = parcelableCreator { Popular }
        }

        /**
         * Shows a list of food of certain [FoodKind]s sorted by specified [NutritionParameter]
         */
        class FilterAndSort(
                private val kinds: Set<FoodKind>,
                private val sortBy: NutritionParameter,
                private val desc: Boolean
        ) : Mode, BoringParcelable {
            override val data: List<FoodItem> get() = getFilteredAndSortedFoodItems(kinds, sortBy, desc)

            override fun writeToParcel(dest: Parcel, flags: Int) {
                dest.writeEnumSet(kinds)
                dest.writeEnum(sortBy)
                dest.writeBoolean(desc)
            }
            companion object {
                @JvmField val CREATOR = parcelableCreator { FilterAndSort(
                        kinds = it.readEnumSet(),
                        sortBy = it.readEnum(),
                        desc = it.readBoolean()
                ) }
            }
        }
    }

}