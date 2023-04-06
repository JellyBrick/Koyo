package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinDailyInfo(
    val totalSignDay: Int,
    val today: String,
    val isSign: Boolean,
    val firstBind: Boolean,
    val isSub: Boolean,
    val region: String,
    val monthLastDay: Boolean,
)
