package be.zvz.koyo.utils

import be.zvz.koyo.constants.Headers
import be.zvz.koyo.types.hoyolab.HoyoLabCookie
import okhttp3.Request

object RequestUtil {
    fun getDefaultWebRequestBuilder(cookie: HoyoLabCookie): Request.Builder = Request.Builder()
        .addHeader("User-Agent", Headers.Web.USER_AGENT)
        .addHeader("x-rpc-app_version", Headers.Web.X_RPC_APP_VERSION)
        .addHeader("x-rpc-client_type", Headers.Web.X_RPC_CLIENT_TYPE)
        .addHeader("x-rpc-language", cookie.mi18nLang?.id ?: Headers.Web.X_RPC_LANGUAGE)
        .addHeader("Cookie", generateCookieFromCookieObject(cookie))

    fun getDefaultAndroidRequestBuilder(): Request.Builder = Request.Builder()
        .addHeader("User-Agent", Headers.Android.USER_AGENT)
        .addHeader("Referer", Headers.Android.REFERER)
        .addHeader("Origin", Headers.Android.ORIGIN)
        .addHeader("X_Requested_With", Headers.Android.X_REQUESTED_WITH)
        .addHeader("x-rpc-app_version", Headers.Android.X_RPC_APP_VERSION)
        .addHeader("app_version", Headers.Android.APP_VERSION)
        .addHeader("client_type", Headers.Android.CLIENT_TYPE)
        .addHeader("x-rpc-client_type", Headers.Android.X_RPC_CLIENT_TYPE)
        .addHeader("x-rpc-channel", Headers.Android.X_RPC_CHANNEL)
        .addHeader("x-rpc-language", "ko-kr")
        .addHeader("x-rpc-sys_version", "12")
        .addHeader("x-rpc-device_id", Headers.Android.X_RPC_DEVICE_ID)
        .addHeader("x-rpc-device_model", Headers.Android.X_RPC_DEVICE_MODEL)

    fun generateCookieFromCookieObject(cookie: HoyoLabCookie) =
        "ltoken=${cookie.ltoken};" +
            " ltuid=${cookie.ltuid};" +
            " cookie_token=${cookie.cookieToken};" +
            " account_id=${cookie.accountId}" +
            cookie.mi18nLang?.let { "; mi18nLang=${it.id}" }.orEmpty() +
            cookie.stuid?.let { "; stuid=$it" }.orEmpty() +
            cookie.loginTicket?.let { "; login_ticket=$it" }.orEmpty()
}
