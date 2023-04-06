package be.zvz.koyo.utils

import be.zvz.koyo.constants.Headers
import be.zvz.koyo.types.hoyolab.HoyoLabCookie
import okhttp3.Request

object RequestUtil {
    fun getDefaultRequestBuilder(cookie: HoyoLabCookie): Request.Builder = Request.Builder()
        .addHeader("User-Agent", Headers.USER_AGENT)
        .addHeader("x-rpc-app_version", Headers.X_RPC_APP_VERSION)
        .addHeader("x-rpc-client_type", Headers.X_RPC_CLIENT_TYPE)
        .addHeader("x-rpc-language", Headers.X_RPC_LANGUAGE)
        .addHeader("Cookie", generateCookieFromCookieObject(cookie))

    private fun generateCookieFromCookieObject(cookie: HoyoLabCookie) =
        "ltoken=${cookie.ltoken};" +
            " ltuid=${cookie.ltuid};" +
            " cookie_token=${cookie.cookieToken};" +
            " account_id=${cookie.accountId}" +
            cookie.mi18nLang?.let { "; mi18nLang=${it.id}" }.orEmpty()
}
