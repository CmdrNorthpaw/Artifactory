package uk.cmdrnorthpaw.artifactory.model.recipies

import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier
import uk.cmdrnorthpaw.artifactory.model.serializers.IdentifierSerializer

@Serializable
@JvmInline
value class RecipeType(val type: @Serializable(IdentifierSerializer::class) Identifier)

@Serializable
object RecipeTypes {
    val BLASTING = mcType("blasting")
    val CAMPFIRE = mcType("campfire_cooking")
    val FURNACE = mcType("smelting")
    val SHAPED_CRAFTING = mcType("crafting_shaped")
    val SHAPELESS_CRAFTING = mcType("crafting_shapeless")
    val SMITHING = mcType("smithing")
    val SMOKING = mcType("smoking")
    val STONECUTTING = mcType("stonecutting")
}
private fun mcType(path: String) = RecipeType(Identifier("minecraft", path))
