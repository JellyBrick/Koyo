package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenshinServerAndRoleId(
    val server: String,
    val roleId: Long,
)
