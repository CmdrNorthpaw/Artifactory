package uk.cmdrnorthpaw.artifactory.recipes

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import uk.cmdrnorthpaw.artifactory.model.RecipeResult
import uk.cmdrnorthpaw.artifactory.model.recipies.Recipe
import uk.cmdrnorthpaw.artifactory.model.recipies.RecipeType
import uk.cmdrnorthpaw.artifactory.model.recipies.RecipeTypes
import uk.cmdrnorthpaw.artifactory.model.serializers.Identifier

@Serializable
class ShapedCrafting private constructor(
    val pattern: List<String>,
    val key: List<Pair<Char, Identifier>>,
    val result: RecipeResult,
    override val group: String
) : Recipe() {
    @Required
    override val type: RecipeType = RecipeTypes.SHAPED_CRAFTING

    init {
        assert(this.pattern.size <= 3)
        assert(pattern.none { it.length != 3 })
        assert(key.none { it.first == ' ' })
    }
}