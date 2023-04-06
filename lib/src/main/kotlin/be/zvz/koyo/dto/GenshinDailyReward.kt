package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinDailyReward(
    val month: Int,
    val now: Long,
    val resign: Boolean,
    val award: GenshinDailyAwardItem,
)
