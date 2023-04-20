package be.zvz.koyo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenshinDailyNote(
    val currentResin: Int,
    val maxResin: Int,
    val resinRecoveryTime: Int, // String, but it always a numeric type
    val finishedTaskNum: Int,
    val totalTaskNum: Int,
    val isExtraTaskRewardReceived: Boolean,
    val remainResinDiscountNum: Int,
    val resinDiscountNumLimit: Int,
    val currentExpeditionNum: Int,
    val maxExpeditionNum: Int,
    val expeditions: List<Expedition>,
    val currentHomeCoin: Int,
    val maxHomeCoin: Int,
    val homeCoinRecoveryTime: String,
    val calendarUrl: String,
    val transformer: Transformer,
) {
    @Serializable
    data class Expedition(
        val avatarSideIcon: String,
        val status: ExpeditionStatus,
        val remainedTime: String,
    ) {
        @Serializable
        enum class ExpeditionStatus {
            @SerialName("Finished")
            FINISHED,

            @SerialName("Ongoing")
            ONGOING,
        }
    }

    @Serializable
    data class Transformer(
        val obtained: Boolean,
        val recoveryTime: RecoveryTime,
        val wiki: String,
        val noticed: Boolean,
        val latestJobId: String,
    ) {
        @Serializable
        data class RecoveryTime(
            @SerialName("Day")
            val day: Int = 0,
            @SerialName("Hour")
            val hour: Int = 0,
            @SerialName("Minute")
            val minute: Int = 0,
            @SerialName("Second")
            val second: Int = 0,
            val reached: Boolean,
        )
    }
}
