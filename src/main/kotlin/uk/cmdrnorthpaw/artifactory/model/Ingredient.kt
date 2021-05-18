package uk.cmdrnorthpaw.artifactory.model

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uk.cmdrnorthpaw.artifactory.model.serializers.Identifier

@Serializable
sealed class Ingredient {
    @Required
    abstract val id: Identifier
}

@Serializable
class ItemIngredient(@SerialName("item") override val id: Identifier) : Ingredient()

@Serializable
class TagIngredient(@SerialName("tag") override val id: Identifier) : Ingredient()