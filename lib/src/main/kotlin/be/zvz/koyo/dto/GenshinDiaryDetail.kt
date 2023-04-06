package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinDiaryDetail(
    override val uid: Long,
    override val region: String,
    override val nickname: String,
    override val optionalMonth: List<Int>,
    override val dataMonth: Long,
    val currentPage: Int,
    val list: List<DiaryHistory>
) : GenshinDiaryInfoBase() {
    @Serializable
    data class DiaryHistory(
        val actionId: Long,
        val action: String,
        val time: String,
        val num: Int,
    )
}
