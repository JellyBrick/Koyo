package be.zvz.koyo.utils

import com.soywiz.krypto.md5
import kotlinx.datetime.Clock

object StringUtil {
    fun genshinUidToRegion(uid: String) = when (uid[0]) {
        '6' -> "os_usa"
        '7' -> "os_euro"
        '8' -> "os_asia"
        '9' -> "os_cht"
        else -> throw IllegalArgumentException("Invalid UID")
    }

    internal fun generateDS(salt: String = "6s25p5ox5y14umn1p61aqyyvbvvl3lrt"): String {
        val date = Clock.System.now().epochSeconds / 1000

        val charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val randomString = (1..6)
            .map { charset.random() }
            .joinToString("")

        val hash = "salt=$salt&t=$date&r=$randomString".encodeToByteArray().md5().hex

        return "$date,$randomString,$hash"
    }

    internal fun randomString(length: Int): String {
        val charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}
