package be.zvz.koyo

import be.zvz.koyo.constants.Routes
import be.zvz.koyo.dto.GenshinGame
import be.zvz.koyo.types.Games
import be.zvz.koyo.types.Language
import be.zvz.koyo.types.hoyolab.HoyoLabOptions
import be.zvz.koyo.utils.AsyncHandler
import be.zvz.koyo.utils.RequestUtil
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.json.decodeFromStream
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okio.IOException

class HoyoLab @JvmOverloads constructor(
    private val options: HoyoLabOptions,
    private val okHttpClient: OkHttpClient = OkHttpClient(),
) {
    @OptIn(ExperimentalSerializationApi::class)
    private val jsonParser = Json {
        ignoreUnknownKeys = true
        namingStrategy = JsonNamingStrategy.SnakeCase
    }

    private fun generateGameListCall(game: Games?) = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(options.cookie)
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

    @OptIn(ExperimentalSerializationApi::class)
    fun gamesList(game: Games? = null): List<GenshinGame> = generateGameListCall(game).execute().use {
        jsonParser.decodeFromStream(it.body.byteStream())
    }

    fun gamesList(game: Games? = null, callback: (List<GenshinGame>) -> Unit, exceptionHandler: ((IOException) -> Unit)? = null) {
        generateGameListCall(game).enqueue(AsyncHandler(jsonParser, ListSerializer(GenshinGame.serializer()), callback, exceptionHandler))
    }

    fun gameAccount(game: Games): GenshinGame = gamesList(game).maxBy { it.level }

    fun gameAccount(game: Games, callback: (GenshinGame) -> Unit, exceptionHandler: ((IOException) -> Unit)? = null) {
        gamesList(game, { callback(it.maxBy { element -> element.level }) }, exceptionHandler)
    }
}
