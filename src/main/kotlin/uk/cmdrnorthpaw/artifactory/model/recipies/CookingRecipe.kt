package uk.cmdrnorthpaw.artifactory.model.recipies

import kotlinx.serialization.SerialName
import uk.cmdrnorthpaw.artifactory.model.Ingredient
import uk.cmdrnorthpaw.artifactory.model.serializers.Identifier

abstract class CookingRecipe : Recipe() {
    @SerialName("ingredient")
    abstract val ingredients: List<Ingredient>

    abstract val result: Identifier

    abstract val experience: Int

    @SerialName("cookingtime")
    abstract val time: Int
}