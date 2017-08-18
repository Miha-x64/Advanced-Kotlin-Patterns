package net.aquadc.advancedkotlinpatterns.recycler

import java.util.Collections.unmodifiableList

class FoodItem(
        val photoUrl: String,
        val title: CharSequence,
        val description: CharSequence,
        val nutritionInfo: NutritionInfo
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
    override fun toString() =
            "Energy: $energy kcal, fats: $fats g, carbohydrates: $carbohydrates, proteins: $proteins g"
}

fun NutritionInfo.getParameter(parameter: NutritionParameter) = when (parameter) {
    NutritionParameter.Energy -> energy
    NutritionParameter.Fats -> fats
    NutritionParameter.Carbohydrates -> carbohydrates
    NutritionParameter.Proteins -> proteins
}

enum class NutritionParameter {
    Energy, Fats, Carbohydrates, Proteins;

    companion object {
        val Values: List<NutritionParameter> = unmodifiableList(values().toList())
    }
}

val foodItems = listOf(
        FoodItem(
                "https://pbs.twimg.com/profile_images/726613174523297792/v0edN9KP_400x400.jpg",
                "Falafel",
                "a deep-fried ball, doughnut or patty made from ground chickpeas, fava beans, or both. " +
                        "Falafel is a traditional Middle Eastern food, commonly served in a pita, " +
                        "which acts as a pocket, or wrapped in a flatbread known as taboon; " +
                        "\"falafel\" also frequently refers to a wrapped sandwich that is prepared in this way.",
                NutritionInfo(energy = 333.0, fats = 18.0, carbohydrates = 32.0, proteins = 13.0)
        ),
        FoodItem(
                "https://bigoven-res.cloudinary.com/image/upload/t_recipe-256/tofu-baked-in-a-lemon-rosemary-43d3fe.jpg",
                "Tofu",
                "a food cultivated by coagulating soy milk and then pressing the resulting curds " +
                        "into soft white blocks. " +
                        "It is a component in East Asian, Southeast Asian and West African cuisines.",
                NutritionInfo(energy = 76.0, fats = 4.8, carbohydrates = 1.9, proteins = 8.0)
        ),
        FoodItem(
                "https://static.wixstatic.com/media/f012eb_646ac521ce864eb8bd2d13477870f2dd~mv2.png/v1/fill/w_256,h_256,al_c,q_90/file.jpg",
                "Cellophane noodles",
                "a type of transparent noodle made from starch " +
                        "(such as mung bean starch, yam, potato starch, cassava, canna or batata starch) and water.",
                NutritionInfo(energy = 351.0, fats = 0.1, carbohydrates = 86.0, proteins = 0.2)
        ),
        FoodItem(
                "https://s-media-cache-ak0.pinimg.com/736x/94/4d/47/944d47afe47ea473d19264dd83ac0235--china-food-tofu-recipes.jpg",
                "Tofu skin",
                "a food product made from soybeans. During the boiling of soy milk, in an open shallow pan, " +
                        "a film or skin forms on the liquid surface.",
                NutritionInfo(energy = 387.0, fats = 19.0, carbohydrates = 12.0, proteins = 42.0)
        ),
        FoodItem(
                "https://upload.wikimedia.org/wikipedia/commons/f/f8/Dal_Makhani.jpg",
                "Dal",
                "is a term in the Indian subcontinent for dried, split pulses (that is, lentils, peas, and beans). " +
                        "The term is also used for various soups prepared from these pulses. " +
                        "These pulses are among the most important staple foods in SAARC countries, " +
                        "and form an important part of Indian, Nepalese, Pakistani, Sri Lankan and Bangladeshi cuisines.",
                NutritionInfo(energy = 347.0, fats = 1.0, carbohydrates = 63.0, proteins = 24.0)
        )
)