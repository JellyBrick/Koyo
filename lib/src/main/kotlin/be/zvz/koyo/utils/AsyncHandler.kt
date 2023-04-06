package be.zvz.koyo.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException

class AsyncHandler<T>(
    private val jsonParser: Json,
    private val serializer: KSerializer<T>,
    private val callback: (T) -> Unit,
    private val exceptionHandler: ((IOException) -> Unit)? = null,
) : Callback {
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
            callback(jsonParser.decodeFromStream(serializer, it.body.byteStream()))
        }
    }
}