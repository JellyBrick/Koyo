package be.zvz.koyo.types.genshin

import be.zvz.koyo.types.hoyolab.HoyoLabCookie
import be.zvz.koyo.types.hoyolab.HoyoLabOptions

class GenshinOptions @JvmOverloads constructor(
    cookie: HoyoLabCookie,
    val uid: Long? = null,
) : HoyoLabOptions(cookie, language = cookie.mi18nLang) {
    @JvmOverloads
    constructor(cookie: String, uid: Long? = null) : this(HoyoLabCookie(cookie), uid)

    @JvmOverloads
    constructor(options: GenshinOptions, uid: Long? = null) : this(options.cookie, uid ?: options.uid)
}
