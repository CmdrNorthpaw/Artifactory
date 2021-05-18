package uk.cmdrnorthpaw.artifactory.recipes

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import net.minecraft.item.Item
import uk.cmdrnorthpaw.artifactory.identifier
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


    class Builder(val result: Item, val amount: Int = 1) {
        private val pattern: MutableList<String> = mutableListOf()
        private val key: MutableList<Pair<Char, Identifier>> = mutableListOf()
        private var group: String = ""

        fun patternLine(line: String): Builder {
            assert(line.length == 3)
            pattern.add(line)
            return this
        }

        fun key(key: Char, value: Identifier): Builder {
            this.key.add(key to value)
            return this
        }

        fun setGroup(group: String): Builder {
            this.group = group
            return this
        }

        fun build(): ShapedCrafting = ShapedCrafting(pattern, key, RecipeResult(result.identifier, amount), group)
    }
}