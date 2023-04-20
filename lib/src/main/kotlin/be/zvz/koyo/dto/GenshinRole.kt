package be.zvz.koyo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenshinRole(
    @SerialName("AvatarUrl")
    val avatarUrl: String = "",
    val nickname: String,
    val region: String,
    val level: Long,
)
