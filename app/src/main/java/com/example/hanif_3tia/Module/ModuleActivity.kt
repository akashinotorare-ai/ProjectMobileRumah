package com.example.hanif_3tia.Module

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hanif_3tia.R
import com.example.hanif_3tia.databinding.ActivityModuleBinding
import java.util.Locale

class ModuleActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityModuleBinding
    private var tts: TextToSpeech? = null
    private var wordToSpeak: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityModuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar Modul 5 & Back Navigation
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Ambil data intent dari ListView ModuleAdapter
        val sender = intent.getStringExtra("SENDER") ?: "Kosakata"
        val message = intent.getStringExtra("MESSAGE") ?: "Penjelasan kata"

        wordToSpeak = sender
        binding.sender.text = sender
        binding.message.text = message

        // Inisialisasi TextToSpeech untuk fitur Dengar (Audio)
        tts = TextToSpeech(this, this)

        // Fitur 1: Bantu Baca (Furigana / Romaji Toggle)
        binding.btnBantuBaca.setOnClickListener {
            if (binding.tvBantuBacaText.visibility == View.VISIBLE) {
                binding.tvBantuBacaText.visibility = View.GONE
                binding.btnBantuBaca.text = "BANTU BACA"
            } else {
                binding.tvBantuBacaText.visibility = View.VISIBLE
                binding.tvBantuBacaText.text = "Pelafalan: [$sender]"
                binding.btnBantuBaca.text = "SEMBUNYIKAN"
            }
        }

        // Fitur 2: Audio Pelafalan (TTS)
        binding.btnAudio.setOnClickListener {
            speakWord(wordToSpeak)
        }

        // Fitur 3: Tandai Sudah Dihafal (+5 XP ke SharedPreferences)
        binding.btnMarkMemorized.setOnClickListener {
            val pref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val currentXp = pref.getInt("user_xp", 1420)
            pref.edit().putInt("user_xp", currentXp + 5).apply()

            binding.btnMarkMemorized.isEnabled = false
            binding.btnMarkMemorized.text = "✓ SUDAH DIHAFAL (+5 XP)"
            binding.btnMarkMemorized.setBackgroundColor(getColor(R.color.Gray_Light))
            Toast.makeText(this, "Hebat! +5 XP ditambahkan ke profilmu 🎉", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.ENGLISH
        }
    }

    private fun speakWord(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}