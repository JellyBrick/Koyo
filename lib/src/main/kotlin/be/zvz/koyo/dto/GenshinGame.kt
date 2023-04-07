package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinGame(
    val gameBiz: String,
    val region: String,
    val gameUid: Long,
    val nickname: String,
    val level: Long,
    val isChosen: Boolean,
    val regionName: String,
    val isOfficial: Boolean,
)
