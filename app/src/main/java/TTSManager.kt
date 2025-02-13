package com.example.voicechat

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import java.util.*

class TTSManager(context: Context) {

    private val tts: TextToSpeech = TextToSpeech(context, OnInitListener { status ->
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    })

    fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}
