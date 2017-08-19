package net.aquadc.advancedkotlinpatterns.recycler

import java.util.Collections.unmodifiableList

val foodItems: List<FoodItem> = unmodifiableList(listOf(
        FoodItem(
                "https://pbs.twimg.com/profile_images/726613174523297792/v0edN9KP_400x400.jpg",
                "Falafel",
                "a deep-fried ball, doughnut or patty made from ground chickpeas, fava beans, or both. " +
                        "Falafel is a traditional Middle Eastern food, commonly served in a pita, " +
                        "which acts as a pocket, or wrapped in a flatbread known as taboon; " +
                        "\"falafel\" also frequently refers to a wrapped sandwich that is prepared in this way.",
                NutritionInfo(energy = 333.0, fats = 18.0, carbohydrates = 32.0, proteins = 13.0),
                FoodKind.Meal
        ),
        FoodItem(
                "https://bigoven-res.cloudinary.com/image/upload/t_recipe-256/tofu-baked-in-a-lemon-rosemary-43d3fe.jpg",
                "Tofu",
                "a food cultivated by coagulating soy milk and then pressing the resulting curds " +
                        "into soft white blocks. " +
                        "It is a component in East Asian, Southeast Asian and West African cuisines.",
                NutritionInfo(energy = 76.0, fats = 4.8, carbohydrates = 1.9, proteins = 8.0),
                FoodKind.Meal
        ),
        FoodItem(
                "https://static.wixstatic.com/media/f012eb_646ac521ce864eb8bd2d13477870f2dd~mv2.png/v1/fill/w_256,h_256,al_c,q_90/file.jpg",
                "Cellophane noodles",
                "a type of transparent noodle made from starch " +
                        "(such as mung bean starch, yam, potato starch, cassava, canna or batata starch) and water.",
                NutritionInfo(energy = 351.0, fats = .1, carbohydrates = 86.0, proteins = .2),
                FoodKind.Meal
        ),
        FoodItem(
                "https://s-media-cache-ak0.pinimg.com/736x/94/4d/47/944d47afe47ea473d19264dd83ac0235--china-food-tofu-recipes.jpg",
                "Tofu skin",
                "a food product made from soybeans. During the boiling of soy milk, in an open shallow pan, " +
                        "a film or skin forms on the liquid surface.",
                NutritionInfo(energy = 387.0, fats = 19.0, carbohydrates = 12.0, proteins = 42.0),
                FoodKind.Meal
        ),
        FoodItem(
                "https://upload.wikimedia.org/wikipedia/commons/f/f8/Dal_Makhani.jpg",
                "Dal",
                "various soups prepared from dried, split pulses.",
                NutritionInfo(energy = 347.0, fats = 1.0, carbohydrates = 63.0, proteins = 24.0),
                FoodKind.Meal
        ),
        FoodItem(
                "http://static.wixstatic.com/media/92eca4_234f5123236c4e2f9032ef39e851d28f.jpg_256",
                "Green tea",
                "is a type of tea that is made from Camellia sinensis leaves " +
                        "that have not undergone the same withering and oxidation process " +
                        "used to make oolong and black tea. " +
                        "Green tea originated in China, but its production has spread to many countries in Asia.",
                NutritionInfo(energy = 2.0, fats = .0, carbohydrates = .47, proteins = .0),
                FoodKind.Drink
        ),
        FoodItem(
                "http://www.rivertea.com/blog/wp-content/uploads/2013/01/black-tea-cup-e1359634422907.jpg",
                "Black tea",
                "is a type of tea that is more oxidized than oolong, green and white teas. " +
                        "Black tea is generally stronger in flavor than the less oxidized teas.",
                NutritionInfo(energy = 1.1, fats = .0, carbohydrates = .3, proteins = .0),
                FoodKind.Drink
        ),
        FoodItem(
                "https://meileaf.com/uploaded/thumbnails/db_file_img_397_365x365_eaeaec.jpg",
                "Oolong",
                "is a traditional Chinese tea (Camellia sinensis) " +
                        "produced through a process including withering the plant under strong sun " +
                        "and oxidation before curling and twisting.",
                NutritionInfo(energy = 1.4, fats = .051, carbohydrates = .04, proteins = .2),
                FoodKind.Drink
        )
))
