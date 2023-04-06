package be.zvz.koyo.dto

import kotlinx.serialization.Serializable

@Serializable
abstract class GenshinDiaryInfoBase {
    abstract val uid: Long
    abstract val region: String
    abstract val nickname: String
    abstract val optionalMonth: List<Int>
    abstract val dataMonth: Long
}
