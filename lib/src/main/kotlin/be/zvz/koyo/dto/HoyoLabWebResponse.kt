package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class HoyoLabWebResponse<T>(
    val code: Int,
    val data: T,
)
