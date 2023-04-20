package be.zvz.koyo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CodeRedemptionResult(
    @SerialName("msg")
    val message: String,
    val specialShipping: Int
)
