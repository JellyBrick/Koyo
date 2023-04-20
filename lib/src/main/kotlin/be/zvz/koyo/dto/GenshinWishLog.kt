package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinWishLog(
    val page: Long,
    val size: Long,
    val total: Long,
    val list: List<Item>,
) {
    @Serializable
    data class Item(
        val uid: Long,
        val gachaType: Int,
        val itemId: String,
        val count: Int,
        val time: String, // It can't use instant because it is not a valid ISO timestamp
        val name: String,
        val lang: String,
        val rankType: Int,
        val id: Long,
    )
}
