package be.zvz.koyo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class HoyoLabLoginByCookie(
    val accountInfo: AccountInfo? = null,
    @SerialName("game_ctrl_info")
    val gameControlInfo: JsonElement? = null,
    val info: String? = null,
    @SerialName("msg")
    val message: String,
    val noticeInfo: JsonElement? = null,
    val status: Int,
) {
    @Serializable
    data class AccountInfo(
        val accountId: Long,
        val areaCode: String,
        val country: String,
        val email: String,
        @SerialName("is_realname")
        val isRealName: Int,
        val safeLevel: Int,
        @SerialName("weblogin_token")
        val webLoginToken: String,
    )
}
