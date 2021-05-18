package uk.cmdrnorthpaw.artifactory.model

import kotlinx.serialization.Serializable
import uk.cmdrnorthpaw.artifactory.model.serializers.Identifier

@Serializable
data class RecipeResult(
    val item: Identifier,
    val count: Int = 1
)