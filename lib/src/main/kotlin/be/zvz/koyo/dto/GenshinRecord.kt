package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinRecord(
    val role: GenshinRole,
    val avatars: List<Avatar>,
    val stats: Stats,
    val worldExplorations: List<WorldExploration>,
    val homes: List<Home>,
    val cityExplorations: List<CityExploration>,
) {

    @Serializable
    data class Avatar(
        val id: Long,
        val image: String,
        val name: String,
        val element: String,
        val fetter: Long,
        val level: Int,
        val rarity: Int,
        val activedConstellationNum: Short,
        val cardImage: String,
        val isChosen: Boolean,
    )

    @Serializable
    data class Stats(
        val activeDayNumber: Int,
        val achievementNumber: Int,
        val anemoculusNumber: Int,
        val geoculusNumber: Int,
        val electroculusNumber: Int,
        val dendroculusNumber: Int,
        val avatarNumber: Int,
        val wayPointNumber: Int,
        val domainNumber: Int,
        val spiralAbyss: String,
        val preciousChestNumber: Int,
        val luxuriousChestNumber: Int,
        val exquisiteChestNumber: Int,
        val magicChestNumber: Int,
        val commonChestNumber: Int,
    )

    @Serializable
    data class WorldExploration(
        val level: Int,
        val explorationPercentage: Int,
        val icon: String,
        val name: String,
        val type: String,
        val offerings: List<Offering>,
        val id: Long,
        val parentId: Long,
        val mapUrl: String,
        val strategyUrl: String,
        val backgroundImage: String,
        val innerIcon: String,
        val cover: String,
    ) {
        @Serializable
        data class Offering(
            val name: String,
            val icon: String,
            val level: Int,
        )
    }

    @Serializable
    data class Home(
        val level: Int,
        val visitNum: Int,
        val comfortNum: Int,
        val itemNum: Int,
        val name: String,
        val icon: String,
        val comfortLevelName: String,
        val comfortLevelIcon: String,
    )

    @Serializable
    data class CityExploration(
        val id: Long,
        val level: Int,
        val explorationPercentage: Int,
        val icon: String,
        val name: String,
    )
}
