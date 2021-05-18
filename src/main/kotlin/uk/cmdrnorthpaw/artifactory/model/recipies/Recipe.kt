package uk.cmdrnorthpaw.artifactory.model.recipies

import kotlinx.serialization.Required

abstract class Recipe {
    abstract val type: RecipeType

    @Required
    open val group: String = ""
}