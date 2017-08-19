package net.aquadc.advancedkotlinpatterns.recycler

import java.util.Collections.unmodifiableList

/**
 * Item of something that can be devoured and digested by a live creature.
 * @param photoUrl      URL of a photo, 256...500 px
 * @param name          name of the dish
 * @param description   brief info about composition, cooking technology, geography & history of a product
 * @param nutritionInfo nutritional value details of a product
 * @param kind          whether this item is a meal, a drink or whatever
 * @param popularity    number of search results in Google, where query is [name]
 */
class FoodItem(
        val photoUrl: String,
        val name: CharSequence,
        val description: CharSequence,
        val nutritionInfo: NutritionInfo,
        val kind: FoodKind,
        val popularity: Long
)

/**
 * Nutrition info per 100g of product.
 * @param energy        in kilo-calories
 * @param fats          in grams
 * @param carbohydrates in grams
 * @param proteins      in grams
 */
class NutritionInfo(
        val energy: Double,
        val fats: Double,
        val carbohydrates: Double,
        val proteins: Double
) {

    // todo: hashCode & equals

    /**
     * Returns human-readable representation.
     * You can't see it but should be aware: it contains no-break spaces (\u00A0).
     */
    override fun toString() =
            "Energy: $energy kcal, " +
                    "fats: $fats g, " +
                    "carbohydrates: $carbohydrates g, " +
                    "proteins: $proteins g"
}

fun NutritionInfo.valueOf(parameter: NutritionParameter) = when (parameter) {
    NutritionParameter.Energy -> energy
    NutritionParameter.Fats -> fats
    NutritionParameter.Carbohydrates -> carbohydrates
    NutritionParameter.Proteins -> proteins
}

fun Sequence<FoodItem>.sortedBy(parameter: NutritionParameter, desc: Boolean) =
        if (desc) sortedByDescending { it.nutritionInfo.valueOf(parameter) }
        else sortedBy { it.nutritionInfo.valueOf(parameter) }

enum class NutritionParameter {
    Energy, Fats, Carbohydrates, Proteins;

    companion object {
        val Values: List<NutritionParameter> = unmodifiableList(values().toList())
    }
}

enum class FoodKind {
    Meal, Drink;

    companion object {
        val Values: List<FoodKind> = unmodifiableList(values().toList())
    }
}
