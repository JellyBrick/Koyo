package be.zvz.koyo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenshinDailyAwardItem(
    val icon: String,
    val name: String,
    @SerialName("cnt")
    val count: Long,
)
