package be.zvz.koyo.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class GenshinRole(
    @SerialName("AvatarUrl")
    val avatarUrl: String,
    val nickname: String,
    val region: String,
    val level: Long,
)
