package be.zvz.koyo.constants

import be.zvz.koyo.types.Language
import com.benasher44.uuid.Uuid

object Headers {
    object Web {
        const val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36"
        const val X_RPC_APP_VERSION = "1.5.0"
        const val X_RPC_CLIENT_TYPE = "5"
        val X_RPC_LANGUAGE = Language.ENGLISH.id
    }

    object Android {
        const val USER_AGENT = "okhttp/4.8.0"
        const val REFERER = "https://app.hoyolab.com"
        const val ORIGIN = "https://app.hoyolab.com"
        const val APP_VERSION = "2.18.1"
        const val X_RPC_APP_VERSION = "2.18.1"
        const val CLIENT_TYPE = "2"
        const val X_RPC_CLIENT_TYPE = "2"
        const val X_RPC_CHANNEL = "hoyolab"
        const val X_REQUESTED_WITH = "com.mihoyo.hoyolab"
        val X_RPC_DEVICE_ID: String
            get() = Uuid.randomUUID().toString()
        const val X_RPC_DEVICE_MODEL = "flo"
    }
}
