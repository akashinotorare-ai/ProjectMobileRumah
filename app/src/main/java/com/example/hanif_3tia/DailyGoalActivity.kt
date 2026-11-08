package com.example.hanif_3tia

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hanif_3tia.databinding.ActivityDailyGoalBinding

class DailyGoalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDailyGoalBinding
    private var selectedGoalMinutes = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDailyGoalBinding.inflate(layoutInflater)
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

        // Default: 15 Menit (Serious)
        updateGoalSelection(15)

        binding.goalCasual.setOnClickListener { updateGoalSelection(5) }
        binding.goalRegular.setOnClickListener { updateGoalSelection(10) }
        binding.goalSerious.setOnClickListener { updateGoalSelection(15) }
        binding.goalIntense.setOnClickListener { updateGoalSelection(20) }

        binding.btnLanjutkan.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putInt("daily_goal_minutes", selectedGoalMinutes)
            // Tandai sesi login aktif (Modul 6)
            editor.putBoolean("isLogin", true)
            editor.apply()

            val intent = Intent(this, BaseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun updateGoalSelection(minutes: Int) {
        selectedGoalMinutes = minutes
        binding.goalCasual.isSelected = minutes == 5
        binding.goalRegular.isSelected = minutes == 10
        binding.goalSerious.isSelected = minutes == 15
        binding.goalIntense.isSelected = minutes == 20
    }
}
