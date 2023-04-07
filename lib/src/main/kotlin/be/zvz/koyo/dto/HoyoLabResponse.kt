package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
data class HoyoLabResponse<T>(
    val retcode: Int,
    val message: String,
    val data: T,
)
