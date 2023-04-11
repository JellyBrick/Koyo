package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class ParsedGenshinDailyReward(
    val month: Int,
    val now: Long,
    val resign: Boolean,
    val award: GenshinDailyAwardItem,
)
