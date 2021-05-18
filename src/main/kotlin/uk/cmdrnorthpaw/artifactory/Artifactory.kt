package uk.cmdrnorthpaw.artifactory

import net.fabricmc.api.ModInitializer
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry
import uk.cmdrnorthpaw.artifactory.model.serializers.Identifier

object Artifactory : ModInitializer {
    override fun onInitialize() {}
}

val Item.identifier: net.minecraft.util.Identifier
    get() = Registry.ITEM.getId(this)