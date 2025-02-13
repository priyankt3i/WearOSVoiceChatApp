package com.example.voicechat

import android.content.Context
import android.speech.SpeechRecognizer
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer.RESULTS_RECOGNITION
import android.speech.SpeechRecognizer.createSpeechRecognizer
import android.widget.Toast

class SpeechRecognitionManager(context: Context, private val onResult: (String) -> Unit) {

    private val speechRecognizer: SpeechRecognizer = createSpeechRecognizer(context)

    init {
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(RESULTS_RECOGNITION)
                matches?.let {
                    onResult(it[0]) // Pass the first result to the callback
                }
            }

            override fun onError(error: Int) {
                Toast.makeText(context, "Error recognizing speech", Toast.LENGTH_SHORT).show()
            }

            // Implement other required methods for the listener here
            override fun onReadyForSpeech(p0: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onEndOfSpeech() {}
            override fun onRmsChanged(p0: Float) {}
            override fun onBufferReceived(p0: ByteArray?) {}
            override fun onEvent(p0: Int, p1: Bundle?) {}
        })
    }

    fun startListening() {
        val intent = android.speech.RecognizerIntent().apply {
            action = android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH
            putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE_MODEL, android.speech.RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        speechRecognizer.startListening(intent)
    }
}
