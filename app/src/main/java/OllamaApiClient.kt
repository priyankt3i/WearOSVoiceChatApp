package com.example.voicechat

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class OllamaApiClient {

    private val client = OkHttpClient()

    fun getAIResponse(input: String, callback: (String) -> Unit) {
        val request = Request.Builder()
            .url("https://ollama.com/api/your-model-endpoint") // Replace with actual Ollama endpoint
            .post(okhttp3.RequestBody.create(null, input))
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback("Error fetching response")
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val responseText = response.body?.string() ?: "No response"
                callback(responseText)
            }
        })
    }
}
