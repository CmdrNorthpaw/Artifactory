package uk.cmdrnorthpaw.artifactory.model.serializers

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import net.minecraft.util.Identifier
import uk.cmdrnorthpaw.artifactory.model.Ingredient
import uk.cmdrnorthpaw.artifactory.model.ItemIngredient
import uk.cmdrnorthpaw.artifactory.model.TagIngredient
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

object IngredientSerializer : JsonContentPolymorphicSerializer<Ingredient>(Ingredient::class) {
    object TagSerializer : KSerializer<TagIngredient> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("tag", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder) = TagIngredient(Identifier.tryParse(decoder.decodeString())!!)
        override fun serialize(encoder: Encoder, value: TagIngredient) {
            encoder.encodeString(value.tag.toString())
        }
    }

    object ItemSerializer : KSerializer<ItemIngredient> {
        override val descriptor = PrimitiveSerialDescriptor("item", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder) = ItemIngredient(Identifier.tryParse(decoder.decodeString())!!)
        override fun serialize(encoder: Encoder, value: ItemIngredient) = encoder.encodeString(value.item.toString())
    }

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Ingredient> {
        return if (element.jsonObject.containsKey("item")) ItemSerializer else TagSerializer
    }
}