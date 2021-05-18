package uk.cmdrnorthpaw.artifactory.model

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import uk.cmdrnorthpaw.artifactory.model.serializers.Identifier
import uk.cmdrnorthpaw.artifactory.model.serializers.IngredientSerializer

@Serializable(IngredientSerializer::class)
sealed class Ingredient {
    abstract val id: Identifier
}

@Serializable(with = IngredientSerializer.ItemSerializer::class)
class ItemIngredient(val item: Identifier) : Ingredient() {
    @Transient
    override val id: Identifier = item
}

@Serializable(with = IngredientSerializer.TagSerializer::class)
class TagIngredient(val tag: Identifier) : Ingredient() {
    override val id: Identifier
        get() = tag
}