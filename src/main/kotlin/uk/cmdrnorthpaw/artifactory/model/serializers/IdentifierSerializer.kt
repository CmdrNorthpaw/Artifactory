package uk.cmdrnorthpaw.artifactory.model.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.minecraft.util.Identifier

object IdentifierSerializer : KSerializer<Identifier> {

    @Serializable
    @JvmInline
    private value class SerializableIdentifier(val identifier: String)

    override val descriptor: SerialDescriptor = SerializableIdentifier.serializer().descriptor

    override fun deserialize(decoder: Decoder): Identifier {
        val serializableIdentifier = decoder.decodeSerializableValue(SerializableIdentifier.serializer())
        return Identifier.tryParse(serializableIdentifier.identifier) ?: throw SerializationException("Invalid identifier decoded!")
    }

    override fun serialize(encoder: Encoder, value: Identifier) {
        encoder.encodeSerializableValue(SerializableIdentifier.serializer(), SerializableIdentifier(value.toString()))
    }

    fun Identifier.serializer() = this@IdentifierSerializer
}