package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class HoyoLabGameList(
    val list: List<HoyoLabGame>
)
