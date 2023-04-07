package be.zvz.koyo

import be.zvz.koyo.constants.Routes
import be.zvz.koyo.dto.GenshinCharacterIdsAndServerAndRoleId
import be.zvz.koyo.dto.GenshinCharacters
import be.zvz.koyo.dto.GenshinDailyClaim
import be.zvz.koyo.dto.GenshinDailyClaimWrapper
import be.zvz.koyo.dto.GenshinDailyInfo
import be.zvz.koyo.dto.GenshinDailyNote
import be.zvz.koyo.dto.GenshinDailyReward
import be.zvz.koyo.dto.GenshinDailyRewards
import be.zvz.koyo.dto.GenshinDiaryDetail
import be.zvz.koyo.dto.GenshinDiaryInfo
import be.zvz.koyo.dto.GenshinRecord
import be.zvz.koyo.dto.GenshinServerAndRoleId
import be.zvz.koyo.dto.HoyoLabResponse
import be.zvz.koyo.types.Games
import be.zvz.koyo.types.Language
import be.zvz.koyo.types.genshin.GenshinAbyssSchedule
import be.zvz.koyo.types.genshin.GenshinDiaryMonth
import be.zvz.koyo.types.genshin.GenshinDiaryType
import be.zvz.koyo.types.genshin.GenshinOptions
import be.zvz.koyo.types.genshin.GenshinRegion
import be.zvz.koyo.types.genshin.GenshinSpiralAbyss
import be.zvz.koyo.utils.AsyncHandler
import be.zvz.koyo.utils.RequestUtil
import be.zvz.koyo.utils.StringUtil
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.json.decodeFromStream
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.internal.commonEmptyRequestBody
import okio.IOException

class GenshinImpact @JvmOverloads constructor(
    options: GenshinOptions,
    private val okHttpClient: OkHttpClient = OkHttpClient(),
) {
    @OptIn(ExperimentalSerializationApi::class)
    private val jsonParser = Json {
        ignoreUnknownKeys = true
        namingStrategy = JsonNamingStrategy.SnakeCase
        isLenient = true
    }
    private val cookie = options.cookie
    private val uid: Long = requireNotNull(options.uid) { "UID is required" }
    private val language = options.language ?: Language.ENGLISH

    val userRegion: GenshinRegion = GenshinRegion.from(StringUtil.genshinUidToRegion(options.uid.toString()))

    private fun generateRecordCall() = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(
                Routes.GENSHIN_GAME_RECORD.toHttpUrl()
                    .newBuilder()
                    .addQueryParameter("server", userRegion.id)
                    .addQueryParameter("role_id", uid.toString())
                    .build(),
            )
            .header("DS", StringUtil.generateDS())
            .get()
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun record(): GenshinRecord = generateRecordCall().execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<GenshinRecord>>(it.body.byteStream())
    }.data

    fun record(callback: (GenshinRecord) -> Unit, exceptionHandler: ((IOException) -> Unit)? = null) =
        generateRecordCall().enqueue(
            AsyncHandler(jsonParser, GenshinRecord.serializer(), callback, exceptionHandler),
        )

    private fun generateCharactersCall() = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(Routes.GENSHIN_CHARACTERS_LIST)
            .header("DS", StringUtil.generateDS())
            .post(
                jsonParser.encodeToString(
                    GenshinServerAndRoleId(
                        server = userRegion.id,
                        roleId = uid,
                    ),
                ).toRequestBody("application/json".toMediaType()),
            )
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun characters(): GenshinCharacters = generateCharactersCall().execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<GenshinCharacters>>(it.body.byteStream())
    }.data

    fun characters(callback: (GenshinCharacters) -> Unit, exceptionHandler: ((IOException) -> Unit)? = null) =
        generateCharactersCall().enqueue(
            AsyncHandler(jsonParser, GenshinCharacters.serializer(), callback, exceptionHandler),
        )

    private fun generateCharactersSummary(
        characterIds: List<Long>,
    ) = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(Routes.GENSHIN_CHARACTERS_SUMMARY)
            .post(
                jsonParser.encodeToString(
                    GenshinCharacterIdsAndServerAndRoleId(
                        characterIds = characterIds,
                        server = userRegion.id,
                        roleId = uid,
                    ),
                ).toRequestBody("application/json".toMediaType()),
            )
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun charactersSummary(
        characterIds: List<Long>,
    ): GenshinCharacters.CharacterSummary = generateCharactersSummary(characterIds).execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<GenshinCharacters.CharacterSummary>>(it.body.byteStream())
    }.data

    fun charactersSummary(
        characterIds: List<Long>,
        callback: (GenshinCharacters.CharacterSummary) -> Unit,
        exceptionHandler: ((IOException) -> Unit)? = null,
    ) = generateCharactersSummary(characterIds).enqueue(
        AsyncHandler(jsonParser, GenshinCharacters.CharacterSummary.serializer(), callback, exceptionHandler),
    )

    private fun generateSpiralAbyssCall(
        scheduleType: GenshinAbyssSchedule,
    ) = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(
                Routes.GENSHIN_SPIRAL_ABYSS.toHttpUrl()
                    .newBuilder()
                    .addQueryParameter("server", userRegion.id)
                    .addQueryParameter("role_id", uid.toString())
                    .addQueryParameter("schedule_type", scheduleType.id.toString())
                    .build(),
            )
            .get()
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun spiralAbyss(
        scheduleType: GenshinAbyssSchedule = GenshinAbyssSchedule.CURRENT,
    ): GenshinSpiralAbyss = generateSpiralAbyssCall(scheduleType).execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<GenshinSpiralAbyss>>(it.body.byteStream())
    }.data

    fun spiralAbyss(
        scheduleType: GenshinAbyssSchedule = GenshinAbyssSchedule.CURRENT,
        callback: (GenshinSpiralAbyss) -> Unit,
        exceptionHandler: ((IOException) -> Unit)? = null,
    ) = generateSpiralAbyssCall(scheduleType).enqueue(
        AsyncHandler(jsonParser, GenshinSpiralAbyss.serializer(), callback, exceptionHandler),
    )

    private fun generateDailyNoteCall() = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(
                Routes.GENSHIN_DAILY_NOTE.toHttpUrl()
                    .newBuilder()
                    .addQueryParameter("server", userRegion.id)
                    .addQueryParameter("role_id", uid.toString())
                    .build(),
            )
            .get()
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun dailyNote(): GenshinDailyNote = generateDailyNoteCall().execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<GenshinDailyNote>>(it.body.byteStream())
    }.data

    fun dailyNote(
        callback: (GenshinDailyNote) -> Unit,
        exceptionHandler: ((IOException) -> Unit)? = null,
    ) = generateDailyNoteCall().enqueue(
        AsyncHandler(jsonParser, GenshinDailyNote.serializer(), callback, exceptionHandler),
    )

    private fun generateDiariesCall(month: GenshinDiaryMonth) = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(
                Routes.GENSHIN_DIARY.toHttpUrl()
                    .newBuilder()
                    .addQueryParameter("server", userRegion.id)
                    .addQueryParameter("role_id", uid.toString())
                    .addQueryParameter("month", month.id.toString())
                    .build(),
            )
            .get()
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun diaries(
        month: GenshinDiaryMonth = GenshinDiaryMonth.CURRENT,
    ): GenshinDiaryInfo = generateDiariesCall(month).execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<GenshinDiaryInfo>>(it.body.byteStream())
    }.data

    fun diaries(
        month: GenshinDiaryMonth = GenshinDiaryMonth.CURRENT,
        callback: (GenshinDiaryInfo) -> Unit,
        exceptionHandler: ((IOException) -> Unit)? = null,
    ) = generateDiariesCall(month).enqueue(
        AsyncHandler(jsonParser, GenshinDiaryInfo.serializer(), callback, exceptionHandler),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun diaryDetail(
        type: GenshinDiaryType,
        month: GenshinDiaryMonth,
    ): GenshinDiaryDetail {
        var page = 1
        var next = true
        var detail: GenshinDiaryDetail
        val historyList = mutableListOf<GenshinDiaryDetail.DiaryHistory>()

        do {
            val response = okHttpClient.newCall(
                RequestUtil.getDefaultRequestBuilder(cookie)
                    .url(
                        Routes.GENSHIN_DIARY_DETAIL.toHttpUrl()
                            .newBuilder()
                            .addQueryParameter("server", userRegion.id)
                            .addQueryParameter("role_id", uid.toString())
                            .addQueryParameter("type", type.id.toString())
                            .addQueryParameter("month", month.id.toString())
                            .addQueryParameter("page", page.toString())
                            .build(),
                    )
                    .get()
                    .build(),
            ).execute().use {
                jsonParser.decodeFromStream<HoyoLabResponse<GenshinDiaryDetail>>(it.body.byteStream())
            }.data
            detail = response
            historyList.addAll(response.list)

            if (response.list.isEmpty()) {
                next = false
            }

            page++
        } while (next)

        historyList.sortBy(GenshinDiaryDetail.DiaryHistory::time)

        return GenshinDiaryDetail(
            uid = detail.uid,
            region = detail.region,
            nickname = detail.nickname,
            optionalMonth = detail.optionalMonth,
            dataMonth = detail.dataMonth,
            currentPage = detail.currentPage,
            list = historyList,
        )
    }

    private fun generateDailyInfo() = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(
                Routes.GENSHIN_DAILY_INFO.toHttpUrl()
                    .newBuilder()
                    .addQueryParameter("act_id", "e202102251931481")
                    .addQueryParameter("lang", language.id)
                    .build(),
            )
            .get()
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun dailyInfo(): GenshinDailyInfo = generateDailyInfo().execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<GenshinDailyInfo>>(it.body.byteStream())
    }.data

    fun dailyInfo(
        callback: (GenshinDailyInfo) -> Unit,
        exceptionHandler: ((IOException) -> Unit)? = null,
    ) = generateDailyInfo().enqueue(
        AsyncHandler(jsonParser, GenshinDailyInfo.serializer(), callback, exceptionHandler),
    )

    private fun generateDailyRewards() = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(
                Routes.GENSHIN_DAILY_REWARD.toHttpUrl()
                    .newBuilder()
                    .addQueryParameter("act_id", "e202102251931481")
                    .addQueryParameter("lang", language.id)
                    .build(),
            )
            .get()
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun dailyRewards(): GenshinDailyRewards = generateDailyRewards().execute().use {
        jsonParser.decodeFromStream<HoyoLabResponse<GenshinDailyRewards>>(it.body.byteStream())
    }.data

    fun dailyRewards(
        callback: (GenshinDailyRewards) -> Unit,
        exceptionHandler: ((IOException) -> Unit)? = null,
    ) = generateDailyRewards().enqueue(
        AsyncHandler(jsonParser, GenshinDailyRewards.serializer(), callback, exceptionHandler),
    )

    private fun generateDailyClaim() = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(
                Routes.GENSHIN_DAILY_CLAIM.toHttpUrl()
                    .newBuilder()
                    .addQueryParameter("act_id", "e202102251931481")
                    .addQueryParameter("lang", language.id)
                    .build(),
            )
            .post(commonEmptyRequestBody)
            .build(),
    )

    fun dailyReward(rewards: GenshinDailyRewards, timestamp: Long = -1): GenshinDailyReward {
        val localDateTime = if (timestamp == -1L) {
            Instant.fromEpochSeconds(rewards.now)
        } else {
            Instant.fromEpochSeconds(timestamp)
        }.toLocalDateTime(TimeZone.of("Asia/Shanghai"))

        val day = localDateTime.dayOfMonth
        return GenshinDailyReward(
            month = rewards.month,
            now = rewards.now,
            resign = rewards.resign,
            award = rewards.awards[day - 1],
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun dailyClaim(): GenshinDailyClaimWrapper {
        val response = generateDailyClaim().execute().body.byteStream().use {
            jsonParser.decodeFromStream<HoyoLabResponse<GenshinDailyClaim>>(it)
        }

        val info = dailyInfo()
        val reward = dailyReward(dailyRewards())

        if (response.retcode == -5003) {
            return GenshinDailyClaimWrapper(
                status = response.message,
                code = -5003,
                reward = reward,
                info = info,
            )
        }

        val dailyClaim: GenshinDailyClaim = response.data

        if (dailyClaim.code.lowercase() == "ok" && response.retcode == 0) {
            return GenshinDailyClaimWrapper(
                status = response.message,
                code = 0,
                reward = reward,
                info = info,
            )
        }

        return GenshinDailyClaimWrapper(
            status = response.message,
            code = response.retcode,
            reward = null,
            info = info,
        )
    }

    private fun generateRedeemCodeCall(redeemCode: String) = okHttpClient.newCall(
        RequestUtil.getDefaultRequestBuilder(cookie)
            .url(
                Routes.GENSHIN_REDEEM_CODE.toHttpUrl()
                    .newBuilder()
                    .addQueryParameter("uid", uid.toString())
                    .addQueryParameter("region", userRegion.id)
                    .addQueryParameter("game_biz", Games.GENSHIN_IMPACT_GLOBAL.id)
                    .addQueryParameter("cdkey", redeemCode)
                    .addQueryParameter("lang", language.id)
                    .addQueryParameter("sLangKey", language.id)
                    .build(),
            )
            .get()
            .build(),
    )

    @OptIn(ExperimentalSerializationApi::class)
    fun redeemCode(redeemCode: String): HoyoLabResponse<String?> = generateRedeemCodeCall(redeemCode).execute().use {
        jsonParser.decodeFromStream(it.body.byteStream())
    }

    fun redeemCode(
        redeemCode: String,
        callback: (String?) -> Unit,
        exceptionHandler: ((IOException) -> Unit)? = null,
    ) = generateRedeemCodeCall(redeemCode).enqueue(
        object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (exceptionHandler != null) {
                    exceptionHandler.invoke(e)
                } else {
                    throw e
                }
            }

            @OptIn(ExperimentalSerializationApi::class)
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    callback(jsonParser.decodeFromStream(it.body.byteStream()))
                }
            }
        },
    )

    companion object {
        @JvmStatic
        fun create(options: GenshinOptions): GenshinImpact {
            val uid = options.uid ?: HoyoLab(options).gameAccount(Games.GENSHIN_IMPACT_GLOBAL).gameUid

            return GenshinImpact(GenshinOptions(options, uid))
        }
    }
}
