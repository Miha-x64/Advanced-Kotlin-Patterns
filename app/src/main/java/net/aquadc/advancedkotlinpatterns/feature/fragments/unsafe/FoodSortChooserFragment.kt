package net.aquadc.advancedkotlinpatterns.feature.fragments.unsafe

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import net.aquadc.advancedkotlinpatterns.MainActivity
import net.aquadc.advancedkotlinpatterns.recycler.NutritionParameter
import org.jetbrains.anko.*

class FoodSortChooserFragment : Fragment() {

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

            button("Show") {
                setOnClickListener {

                    val fragment = SortedFoodFragment()
                    fragment.arguments = Bundle(1).apply {
                        putSerializable(SortedFoodFragment.NutritionParamKey, nutritionParameterSpinner.selectedItem as NutritionParameter)
                        putBoolean(SortedFoodFragment.SortDescKey, descendingCheck.isChecked)
                    }

                    fragmentManager
                            .beginTransaction()
                            .replace(MainActivity.FragmentContainerId, fragment)
                            .addToBackStack(null)
                            .commit()
                }
            }

        }

    }.view

}