package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinDailyRewards(
    val month: Int,
    val resign: Boolean,
    val now: Long,
    val awards: List<GenshinDailyAwardItem>,
)
