package net.aquadc.advancedkotlinpatterns.feature.fragments

import net.aquadc.advancedkotlinpatterns.recycler.FoodKind
import net.aquadc.advancedkotlinpatterns.recycler.NutritionParameter
import net.aquadc.advancedkotlinpatterns.recycler.foodItems
import net.aquadc.advancedkotlinpatterns.recycler.sortedBy

fun getSortedByPopularityFoodItems() = foodItems.sortedByDescending { it.popularity }
fun getFilteredAndSortedFoodItems(kinds: Set<FoodKind>, sortBy: NutritionParameter, desc: Boolean) =
        foodItems
                .asSequence()
                .filter { it.kind in kinds }
                .sortedBy(sortBy, desc)
                .toList()