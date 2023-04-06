package be.zvz.koyo.constants

object Routes {
    private const val GENSHIN_GAME_RECORD_URL = "https://bbs-api-os.hoyolab.com/game_record/genshin/api"
    private const val GENSHIN_HKE_URL = "https://sg-hk4e-api.hoyolab.com"

    const val GAMES_ACCOUNT = "https://api-account-os.hoyolab.com/account/binding/api/getUserGameRolesByCookieToken"

    const val GENSHIN_GAME_RECORD_REFERER = "https://act.hoyolab.com"
    const val GENSHIN_GAME_RECORD = "$GENSHIN_GAME_RECORD_URL/index"
    const val GENSHIN_CHARACTERS_LIST = "$GENSHIN_GAME_RECORD_URL/character"
    const val GENSHIN_CHARACTERS_SUMMARY = "$GENSHIN_GAME_RECORD_URL/avatarBasicInfo"
    const val GENSHIN_SPIRAL_ABYSS = "$GENSHIN_GAME_RECORD_URL/spiralAbyss"
    const val GENSHIN_DAILY_NOTE = "$GENSHIN_GAME_RECORD_URL/dailyNote"

    const val GENSHIN_DIARY = "$GENSHIN_HKE_URL/event/ysledgeros/month_info"
    const val GENSHIN_DIARY_DETAIL = "$GENSHIN_HKE_URL/event/ysledgeros/month_detail"

    const val GENSHIN_DAILY_INFO = "$GENSHIN_HKE_URL/event/sol/info"
    const val GENSHIN_DAILY_REWARD = "$GENSHIN_HKE_URL/event/sol/home"
    const val GENSHIN_DAILY_CLAIM = "$GENSHIN_HKE_URL/event/sol/sign"

    const val GENSHIN_REDEEM_CODE = "$GENSHIN_HKE_URL/common/apicdkey/api/webExchangeCdkey"
}
