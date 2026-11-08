package com.example.hanif_3tia

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hanif_3tia.databinding.ActivityChooseLanguageBinding

class ChooseLanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseLanguageBinding
    private var selectedLanguage = "English"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChooseLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Default: English aktif
        updateSelection("English")

        binding.cardEnglish.setOnClickListener {
            updateSelection("English")
        }

        binding.cardJapanese.setOnClickListener {
            updateSelection("Japanese")
        }

        binding.btnLanjutkan.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putString("selected_language", selectedLanguage)
            editor.apply()

            startActivity(Intent(this, DailyGoalActivity::class.java))
            finish()
        }
    }

    private fun updateSelection(language: String) {
        selectedLanguage = language
        val isEnglish = language == "English"

        binding.cardEnglish.isSelected = isEnglish
        binding.cardJapanese.isSelected = !isEnglish

        if (isEnglish) {
            binding.badgeEnglish.setBackgroundResource(R.drawable.bg_btn_yellow)
            binding.badgeEnglish.setTextColor(ContextCompat.getColor(this, R.color.Black_Solid))
            binding.badgeJapanese.setBackgroundResource(R.drawable.bg_btn_surface)
            binding.badgeJapanese.setTextColor(ContextCompat.getColor(this, R.color.Gray))
        } else {
            binding.badgeJapanese.setBackgroundResource(R.drawable.bg_btn_yellow)
            binding.badgeJapanese.setTextColor(ContextCompat.getColor(this, R.color.Black_Solid))
            binding.badgeEnglish.setBackgroundResource(R.drawable.bg_btn_surface)
            binding.badgeEnglish.setTextColor(ContextCompat.getColor(this, R.color.Gray))
        }
    }
}
