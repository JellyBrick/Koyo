package be.zvz.koyo

import be.zvz.koyo.constants.Routes
import be.zvz.koyo.dto.HoyoLabGame
import be.zvz.koyo.dto.HoyoLabGameList
import be.zvz.koyo.dto.HoyoLabResponse
import be.zvz.koyo.types.Games
import be.zvz.koyo.types.Language
import be.zvz.koyo.types.hoyolab.HoyoLabOptions
import be.zvz.koyo.utils.AsyncHandler
import be.zvz.koyo.utils.RequestUtil
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.json.decodeFromStream
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okio.IOException

@OptIn(ExperimentalSerializationApi::class)
open class HoyoLab @JvmOverloads constructor(
    protected val options: HoyoLabOptions,
    protected val okHttpClient: OkHttpClient = OkHttpClient(),
    protected val jsonParser: Json = Json {
        ignoreUnknownKeys = true
        namingStrategy = JsonNamingStrategy.SnakeCase
        isLenient = true
    },
) {

    private fun generateGameListCall(game: Games?) = okHttpClient.newCall(
        RequestUtil.getDefaultWebRequestBuilder(options.cookie)
            .url(
                Routes.GAMES_ACCOUNT.toHttpUrl()
                    .newBuilder()
                    .apply {
                        if (game != null) {
                            addQueryParameter("game_biz", game.id)
                        }
                    }
                    .addQueryParameter("uid", options.cookie.ltuid)
                    .addQueryParameter("sLangKey", (options.cookie.mi18nLang ?: Language.ENGLISH).id)
                    .build(),
            )
            .get()
            .build(),
    )

    fun gamesList(game: Games? = null): HoyoLabGameList = generateGameListCall(game).execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<HoyoLabGameList>>(it.body.byteStream()).data
    }

    fun gamesList(game: Games? = null, callback: (HoyoLabGameList) -> Unit, exceptionHandler: ((IOException) -> Unit)? = null) {
        generateGameListCall(game).enqueue(AsyncHandler(jsonParser, HoyoLabGameList.serializer(), callback, exceptionHandler))
    }

    fun gameAccount(game: Games): HoyoLabGame = gamesList(game).list.maxBy { it.level }

    fun gameAccount(game: Games, callback: (HoyoLabGame) -> Unit, exceptionHandler: ((IOException) -> Unit)? = null) {
        gamesList(game, { callback(it.list.maxBy { element -> element.level }) }, exceptionHandler)
    }
}
