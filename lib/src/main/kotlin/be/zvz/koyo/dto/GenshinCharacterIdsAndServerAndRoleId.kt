package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinCharacterIdsAndServerAndRoleId(
    val characterIds: List<Long>,
    val server: String,
    val roleId: Long,
)
