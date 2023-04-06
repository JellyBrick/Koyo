package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinDailyClaimWrapper(
    val status: String,
    val code: Int,
    val reward: GenshinDailyReward?,
    val info: GenshinDailyInfo,
)
