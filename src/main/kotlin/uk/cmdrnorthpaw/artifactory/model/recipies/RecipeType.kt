package uk.cmdrnorthpaw.artifactory.model.recipies

import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier
import uk.cmdrnorthpaw.artifactory.model.serializers.IdentifierSerializer

@Serializable
@JvmInline
value class RecipeType(val type: @Serializable(IdentifierSerializer::class) Identifier)

@Serializable
enum class RecipeTypes(val type: RecipeType) {
    BLASTING(mcType("blasting")),
    CAMPFIRE(mcType("campfire_cooking")),
    FURNACE(mcType("smelting")),
    SHAPED_CRAFTING(mcType("crafting_shaped")),
    SHAPELESS_CRAFTING(mcType("crafting_shapeless")),
    SMITHING(mcType("smithing")),
    SMOKING(mcType("smoking")),
    STONECUTTING(mcType("stonecutting"))
}
private fun mcType(path: String) = RecipeType(Identifier("minecraft", path))
