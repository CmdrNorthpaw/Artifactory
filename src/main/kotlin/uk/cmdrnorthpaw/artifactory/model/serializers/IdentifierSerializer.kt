package uk.cmdrnorthpaw.artifactory.model.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.minecraft.util.Identifier

object IdentifierSerializer : KSerializer<Identifier> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Identifier", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Identifier {
        return Identifier.tryParse(decoder.decodeString()) ?: throw SerializationException("Invalid identifier decoded!")
    }

    override fun serialize(encoder: Encoder, value: Identifier) {
        encoder.encodeString(value.toString())
    }

    fun Identifier.serializer() = this@IdentifierSerializer
}

typealias Identifier = @Serializable(IdentifierSerializer::class) Identifier