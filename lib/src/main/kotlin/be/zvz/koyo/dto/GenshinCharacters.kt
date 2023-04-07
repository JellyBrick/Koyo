package be.zvz.koyo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenshinCharacters(
    val avatars: List<CharacterAvatarFull>,
    val role: GenshinRole,
) {
    @Serializable
    abstract class CharacterBase {
        abstract val id: Long
        abstract val image: String
        abstract val icon: String
        abstract val name: String
        abstract val element: String
        abstract val rarity: Int
    }

    @Serializable
    data class CharacterAvatarFull(
        override val id: Long,
        override val image: String,
        override val icon: String,
        override val name: String,
        override val element: String,
        override val rarity: Int,
        val fetter: Int,
        val level: Int,
        val weapon: CharacterWeapon,
        val reliquaries: List<CharacterReliquaries>,
        val constellation: List<CharacterConstellation>,
        val activedConstellationNum: Int,
        val costumes: List<CharacterCostume>,
    ) : CharacterBase() {
        @Serializable
        data class CharacterWeapon(
            val id: Long,
            val name: String,
            val icon: String,
            val type: Int,
            val rarity: Int,
            val level: Int,
            val promoteLevel: Int,
            val typeName: String,
            @SerialName("desc")
            val description: String,
            val affixLevel: Int,
        )

        @Serializable
        data class CharacterReliquaries(
            val id: Long,
            val name: String,
            val icon: String,
            val pos: Int,
            val rarity: Int,
            val level: Int,
            val set: CharacterReliquariesSet,
            val posName: String,
        ) {
            @Serializable
            data class CharacterReliquariesSet(
                val id: Long,
                val name: String,
                val affixes: List<CharacterReliquariesAffix>,
            ) {
                @Serializable
                data class CharacterReliquariesAffix(
                    val activationNumber: Int,
                    val effect: String,
                )
            }
        }

        @Serializable
        data class CharacterConstellation(
            val id: Long,
            val name: String,
            val icon: String,
            val effect: String,
            val isActived: Boolean,
            val pos: Int,
        )

        @Serializable
        data class CharacterCostume(
            val id: Long,
            val name: String,
            val icon: String,
        )
    }

    @Serializable
    data class CharacterAvatarSummary(
        override val id: Long,
        override val image: String,
        override val icon: String,
        override val name: String,
        override val element: String,
        override val rarity: Int,
        val weaponType: Int,
        val weaponTypeName: String,
    ) : CharacterBase()

    @Serializable
    data class CharacterSummary(
        val avatars: List<CharacterAvatarSummary>,
    )
}
