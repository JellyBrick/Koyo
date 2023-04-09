package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class HoyoLabAuthKeyRequest(
    val authAppid: String,
    val gameBiz: String,
    val gameUid: Long,
    val region: String,
)
