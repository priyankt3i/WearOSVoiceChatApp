package com.example.voicechat

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.speech.RecognitionService
import com.example.voicechat.ui.theme.WearOSVoiceChatAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var speechRecognitionManager: SpeechRecognitionManager
    private lateinit var ttsManager: TTSManager
    private lateinit var ollamaApiClient: OllamaApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearOSVoiceChatAppTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        Column {
            Button(onClick = {
                startSpeechRecognition()
            }) {
                Text("Start Listening")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                speakResponse("How can I assist you today?")
            }) {
                Text("Speak a Response")
            }
        }
    }

    private fun startSpeechRecognition() {
        speechRecognitionManager = SpeechRecognitionManager(this) { recognizedText ->
            processSpeechInput(recognizedText)
        }
        speechRecognitionManager.startListening()
    }

    private fun processSpeechInput(input: String) {
        ollamaApiClient.getAIResponse(input) { response ->
            speakResponse(response)
        }
    }

    private fun speakResponse(response: String) {
        ttsManager = TTSManager(this)
        ttsManager.speak(response)
    }
}
