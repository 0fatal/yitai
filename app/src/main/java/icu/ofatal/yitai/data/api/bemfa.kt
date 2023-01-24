package icu.ofatal.yitai.data.api

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


enum class Command(val value: String) {
    OFF("off"),
    TWINKLE("twinkle"),
    FRONT("front"),
    BACK("back"),
    STOP("stop")
}

class Bemfa @Inject constructor(
    private val client: OkHttpClient
) {
    private val URLENCODED: MediaType = "application/x-www-form-urlencoded".toMediaType()

    companion object {
        const val UID = ""
        const val TOPIC_COMMAND = "switch"
        const val TOPIC_DATA = "data"
    }

    private fun postData(command: Command) {
        val body: RequestBody =
            "uid=${UID}&topic=${TOPIC_COMMAND}&type=3&msg=${command}".toRequestBody(URLENCODED)
        try {
            val request = Request.Builder()
                .url("https://apis.bemfa.com/va/postmsg")
                .post(body)
                .build()
            client.newCall(request).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getData() {
        val query = "uid=${UID}&topic=${TOPIC_DATA}&type=3"
        try {
            val request = Request.Builder()
                .url("https://apis.bemfa.com/va/getmsg?${query}")
                .get()
                .build()
            client.newCall(request).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stop() {
        return postData(Command.STOP)
    }

    fun twinkle() {
        return postData(Command.TWINKLE)
    }

    fun off() {
        return postData(Command.OFF)
    }

    fun front() {
        return postData(Command.FRONT)
    }

    fun back() {
        return postData(Command.BACK)
    }
}


