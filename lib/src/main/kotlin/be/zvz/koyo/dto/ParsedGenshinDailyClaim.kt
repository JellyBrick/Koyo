package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class ParsedGenshinDailyClaim(
    val status: String,
    val code: Int,
    val reward: ParsedGenshinDailyReward?,
    val info: GenshinDailyInfo,
)
