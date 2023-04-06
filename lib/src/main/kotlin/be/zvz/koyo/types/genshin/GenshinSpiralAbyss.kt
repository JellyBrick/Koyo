package be.zvz.koyo.types.genshin

import kotlinx.serialization.Serializable

@Serializable
data class GenshinSpiralAbyss(
    val scheduleId: Long,
    val startTime: String,
    val endTime: String,
    val totalBattleTimes: Long,
    val totalWinTimes: Long,
    val maxFloor: String,
    val revealRank: List<SpiralAbyssRank>,
    val defeatRank: List<SpiralAbyssRank>,
    val damageRank: List<SpiralAbyssRank>,
    val takeDamageRank: List<SpiralAbyssRank>,
    val normalSkillRank: List<SpiralAbyssRank>,
    val energySkillRank: List<SpiralAbyssRank>,
    val floors: List<SpiralAbyssFloor>,
    val totalStar: Int,
    val isUnlock: Boolean,
) {
    @Serializable
    data class SpiralAbyssRank(
        val avatarId: Long,
        val avatarIcon: String,
        val value: Long,
        val rarity: Int,
    )

    @Serializable
    data class SpiralAbyssFloor(
        val index: Int,
        val icon: String,
        val isUnlock: Boolean,
        val settleTime: String,
        val star: Int,
        val maxStar: Int,
        val levels: List<SpiralAbyssLevel>,
    ) {
        @Serializable
        data class SpiralAbyssLevel(
            val index: Int,
            val star: Int,
            val maxStar: Int,
            val battles: List<SpiralAbyssBattle>,
        ) {
            @Serializable
            data class SpiralAbyssBattle(
                val index: Int,
                val timestamp: String,
                val avatars: List<SpiralAbyssAvatar>,
            ) {
                @Serializable
                data class SpiralAbyssAvatar(
                    val id: Long,
                    val icon: String,
                    val level: Int,
                    val rarity: Int,
                )
            }
        }
    }
}
