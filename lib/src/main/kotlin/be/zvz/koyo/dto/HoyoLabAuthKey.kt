package be.zvz.koyo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoyoLabAuthKey(
    val authkey: String,
    @SerialName("authkey_ver")
    val authkeyVersion: String,
    val signType: Int
)
