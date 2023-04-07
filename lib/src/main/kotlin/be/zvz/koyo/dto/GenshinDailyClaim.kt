package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinDailyClaim(
    val code: String,
    val firstBind: Boolean = false,
)
