package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinDiaryInfo(
    override val uid: Long,
    override val region: String,
    override val nickname: String,
    override val optionalMonth: List<Int>,
    override val dataMonth: Long,
    val month: Int,
    val monthData: MonthData,
    val dayData: DayData,
) : GenshinDiaryInfoBase() {
    @Serializable
    data class MonthData(
        val currentPrimogems: Int,
        val currentMora: Int,
        val lastPrimogems: Int,
        val lastMora: Int,
        val primogemRate: Int,
        val moraRate: Int,
        val groupBy: List<GroupBy>,
    ) {
        @Serializable
        data class GroupBy(
            val actionId: Long,
            val action: String,
            val num: Int,
            val percent: Int,
        )
    }

    @Serializable
    data class DayData(
        val currentPrimogems: Long,
        val currentMora: Long,
    )
}
