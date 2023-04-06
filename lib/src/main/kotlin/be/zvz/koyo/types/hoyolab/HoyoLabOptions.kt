package be.zvz.koyo.types.hoyolab

import be.zvz.koyo.types.Language

open class HoyoLabOptions @JvmOverloads constructor(
    val cookie: HoyoLabCookie,
    val language: Language? = null,
) {
    constructor(cookie: String) : this(HoyoLabCookie(cookie))
}
