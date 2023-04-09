package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class HoyoLabMultiTokenByLoginTicket(
    val list: List<Token>
) {
    @Serializable
    data class Token(
        val name: String,
        val token: String,
    )
}
