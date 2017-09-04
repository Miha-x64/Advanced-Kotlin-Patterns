package net.aquadc.advancedkotlinpatterns.feature.fragments.safe

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.FrameLayout
import net.aquadc.advancedkotlinpatterns.common.replaceAndCommit
import net.aquadc.advancedkotlinpatterns.recycler.FoodKind
import net.aquadc.advancedkotlinpatterns.recycler.NutritionParameter
import org.jetbrains.anko.*
import java.util.*

class FoodFilterAndSortChooserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        verticalLayout {
            layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent)
            padding = dip(16)

            textView("Sort by").lparams(width = matchParent)

            val nutritionParameterSpinner = spinner {
                id = 1
                adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, NutritionParameter.Values).apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
            }

            val descendingCheck = checkBox("Descending") {
                id = 2
                isChecked = true
            }

            textView("Include").lparams(width = matchParent) {
                topMargin = dip(16)
            }

            var lastId = 2
            val foodChecks = EnumMap<FoodKind, CheckBox>(FoodKind::class.java)
            FoodKind.Values.forEach {
                foodChecks[it] = checkBox(it.name) {
                    id = ++lastId
                    isChecked = true
                }
            }

            button("Show") {
                setOnClickListener {

                    val fragment = FoodListFragment(
                            FoodListFragment.DataSource.FilterAndSort(
                                    kinds = foodChecks.filterValues { it.isChecked }.keys,
                                    sortBy = nutritionParameterSpinner.selectedItem as NutritionParameter,
                                    desc = descendingCheck.isChecked
                            )
                    )

                    fragmentManager
                            .replaceAndCommit(fragment)
                }
            }

        }

    }.view

}