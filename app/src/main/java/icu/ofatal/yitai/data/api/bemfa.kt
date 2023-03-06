package icu.ofatal.yitai.data.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import icu.ofatal.yitai.data.model.PressValue
import icu.ofatal.yitai.data.model.ResWrapper
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


enum class Command(val value: String) {
    OFF("off"),
    TWINKLE("twinkle"),
    FRONT("front"),
    BACK("back"),
    STOP("stop")
}

@Singleton
class Bemfa @Inject constructor() {
    private val client = OkHttpClient()
    private val URLENCODED: MediaType = "application/x-www-form-urlencoded".toMediaType()

    companion object {
        const val UID = ""
        const val TOPIC_COMMAND = "switch"
        const val TOPIC_DATA = "data"
    }

    private fun postData(command: Command, completion: ((IOException?) -> Unit)? = null) {
        val body: RequestBody =
            "uid=${UID}&topic=${TOPIC_COMMAND}&type=3&msg=${command.value}".toRequestBody(URLENCODED)

        val request = Request.Builder()
            .url("https://apis.bemfa.com/va/postmsg")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                if (completion != null) {
                    completion(e)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (completion != null) {
                    completion(null)
                }
            }
        })
    }

    fun getData(completion: (List<Double>?, IOException?) -> Unit) {
        val query = "uid=${UID}&topic=${TOPIC_DATA}&type=3"

        val request = Request.Builder()
            .url("https://apis.bemfa.com/va/getmsg?${query}")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                completion(null, e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val data: ResWrapper<List<PressValue>> = Gson().fromJson(
                        it.body!!.string(),
                        object : TypeToken<ResWrapper<List<PressValue>>>() {}.type
                    )
                    if(data.data != null && data.data.isNotEmpty()) {
                        completion(data.data[0].msg.split("#").filter { v -> v.isNotEmpty() }
                            .map { v -> v.toDouble() }, null)
                    }

                }
            }
        })
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

    fun front(completion: ((IOException?) -> Unit)? = null) {
        return postData(Command.FRONT, completion)
    }

    fun back(completion: ((IOException?) -> Unit)? = null) {
        return postData(Command.BACK, completion)
    }

}


