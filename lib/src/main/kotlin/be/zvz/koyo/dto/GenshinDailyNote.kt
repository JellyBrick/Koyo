package be.zvz.koyo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenshinDailyNote(
    val currentResin: Int,
    val maxResin: Int,
    val resinRecoveryTime: String,
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
            val day: Int,
            @SerialName("Hour")
            val hour: Int,
            @SerialName("Minute")
            val minute: Int,
            @SerialName("Second")
            val second: Int,
            val reached: Boolean,
        )
    }
}
