package uk.cmdrnorthpaw.artifactory.model

import kotlinx.serialization.KSerializer

fun interface Serializable<T> {
    fun serializer(): KSerializer<T>
}