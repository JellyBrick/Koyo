package be.zvz.koyo.types.hoyolab

import be.zvz.koyo.types.Language

class HoyoLabCookie {
    val ltoken: String
    val ltuid: String
    val cookieToken: String?
    val accountId: Long
    val mi18nLang: Language?

    @JvmOverloads
    constructor(
        ltoken: String,
        ltuid: String,
        cookieToken: String? = null,
        accountId: Long = -1,
        mi18nLang: Language? = null,
    ) {
        this.ltoken = ltoken
        this.ltuid = ltuid
        this.cookieToken = cookieToken
        this.accountId = accountId
        this.mi18nLang = mi18nLang
    }
    constructor(cookie: String) {
        val cookieMap = cookie.split("; ").associate {
            val (key, value) = it.split("=")
            key to value
        }

        ltoken = cookieMap["ltoken"] ?: throw IllegalArgumentException("Invalid cookie")
        ltuid = cookieMap["ltuid"] ?: throw IllegalArgumentException("Invalid cookie")
        cookieToken = cookieMap["cookie_token"]
        accountId = cookieMap["account_id"]?.toLongOrNull() ?: -1
        mi18nLang = cookieMap["mi18nLang"]?.let { Language from it }
    }
}
