package com.example.hanif_3tia.Quiz

import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hanif_3tia.R
import com.example.hanif_3tia.databinding.ActivityQuizSessionBinding
import com.google.android.material.button.MaterialButton

data class QuizQuestion(
    val prompt: String,
    val word: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

class QuizSessionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizSessionBinding
    private var currentQuestionIndex = 0
    private var selectedOptionIndex = -1
    private var earnedXp = 0
    private var hearts = 5

    private val questions = listOf(
        QuizQuestion(
            prompt = "Pilih terjemahan yang tepat:",
            word = "Good Morning (おはよう)",
            options = listOf("A. Selamat Siang", "B. Selamat Pagi", "C. Terima Kasih", "D. Selamat Tinggal"),
            correctIndex = 1,
            explanation = "Good Morning / おはよう (Ohayou) digunakan sebagai sapaan pagi hari."
        ),
        QuizQuestion(
            prompt = "Manakah ungkapan terima kasih?",
            word = "Thank You (ありがとう)",
            options = listOf("A. Arigatou", "B. Konnichiwa", "C. Sayounara", "D. Gakkou"),
            correctIndex = 0,
            explanation = "Thank You / ありがとう (Arigatou) adalah ungkapan terima kasih dalam Bahasa Jepang."
        ),
        QuizQuestion(
            prompt = "Apa arti kata ini?",
            word = "Tomodachi (ともだち)",
            options = listOf("A. Guru", "B. Air", "C. Sahabat / Teman", "D. Buku"),
            correctIndex = 2,
            explanation = "Tomodachi (ともだち) berarti teman atau sahabat."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnClose.setOnClickListener {
            finish()
        }

        setupOptionButtons()
        loadQuestion(currentQuestionIndex)

        // Tombol Periksa Jawaban
        binding.btnCheckAnswer.setOnClickListener {
            if (selectedOptionIndex == -1) {
                Toast.makeText(this, "Silakan pilih salah satu jawaban!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            checkAnswer()
        }

        // Tombol Lanjut ke Soal Berikutnya
        binding.btnNextQuestion.setOnClickListener {
            binding.layoutFeedback.visibility = View.GONE
            binding.btnCheckAnswer.visibility = View.VISIBLE
            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                loadQuestion(currentQuestionIndex)
            } else {
                finishQuizSession()
            }
        }
    }

    private fun setupOptionButtons() {
        val buttons = listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4)
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                selectOption(index)
            }
        }
    }

    private fun selectOption(index: Int) {
        selectedOptionIndex = index
        val buttons = listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4)
        val yellowColor = ContextCompat.getColor(this, R.color.Yellow)
        val grayColor = ContextCompat.getColor(this, R.color.Gray_Light)

        buttons.forEachIndexed { i, btn ->
            if (i == index) {
                btn.strokeColor = ContextCompat.getColorStateList(this, R.color.Yellow)
                btn.strokeWidth = 4
                btn.setBackgroundColor(Color.parseColor("#FFFDE7"))
            } else {
                btn.strokeColor = ContextCompat.getColorStateList(this, R.color.Gray_Light)
                btn.strokeWidth = 2
                btn.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    private fun loadQuestion(index: Int) {
        selectedOptionIndex = -1
        val q = questions[index]

        binding.tvPromptTitle.text = q.prompt
        binding.tvQuestionWord.text = q.word
        binding.progressBarQuiz.progress = ((index + 1) * 100) / questions.size

        val buttons = listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4)
        buttons.forEachIndexed { i, btn ->
            btn.text = q.options[i]
            btn.strokeColor = ContextCompat.getColorStateList(this, R.color.Gray_Light)
            btn.strokeWidth = 2
            btn.setBackgroundColor(Color.TRANSPARENT)
            btn.isEnabled = true
        }
    }

    private fun checkAnswer() {
        val q = questions[currentQuestionIndex]
        val isCorrect = selectedOptionIndex == q.correctIndex

        val buttons = listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4)
        buttons.forEach { it.isEnabled = false }

        binding.btnCheckAnswer.visibility = View.GONE
        binding.layoutFeedback.visibility = View.VISIBLE

        if (isCorrect) {
            earnedXp += 10
            binding.layoutFeedback.setBackgroundColor(Color.parseColor("#D1FADF"))
            binding.tvFeedbackStatus.text = "Jawaban Benar! +10 XP 🎉"
            binding.tvFeedbackStatus.setTextColor(Color.parseColor("#05603A"))
            binding.tvFeedbackExplanation.text = q.explanation
            binding.tvFeedbackExplanation.setTextColor(Color.parseColor("#05603A"))
            binding.btnNextQuestion.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Green_Success)
        } else {
            hearts--
            binding.tvQuizHeart.text = "❤️ $hearts"
            binding.layoutFeedback.setBackgroundColor(Color.parseColor("#FEE2E2"))
            binding.tvFeedbackStatus.text = "Ups, Kurang Tepat! 😅"
            binding.tvFeedbackStatus.setTextColor(Color.parseColor("#B42318"))
            binding.tvFeedbackExplanation.text = "Jawaban benar adalah: ${q.options[q.correctIndex]}"
            binding.tvFeedbackExplanation.setTextColor(Color.parseColor("#B42318"))
            binding.btnNextQuestion.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Red_Error)
        }
    }

    private fun finishQuizSession() {
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val currentXp = sharedPref.getInt("user_xp", 1420)
        val editor = sharedPref.edit()
        editor.putInt("user_xp", currentXp + earnedXp)
        editor.apply()

        AlertDialog.Builder(this)
            .setTitle("Sesi Belajar Selesai! 🎉")
            .setMessage("Kerja bagus! Kamu berhasil menyelesaikan sesi kuis Bangbo dan mendapatkan +$earnedXp XP!\nTarget harianmu semakin dekat.")
            .setPositiveButton("Kembali ke Beranda") { d, _ ->
                d.dismiss()
                finish()
            }
            .setCancelable(false)
            .show()
    }
}
