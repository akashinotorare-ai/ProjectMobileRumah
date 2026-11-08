package com.example.hanif_3tia.Quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hanif_3tia.databinding.FragmentQuizBinding
import com.google.android.material.chip.Chip

class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Modul 9 Tahap 2.2: Deteksi Chip Filter yang Dipilih
        binding.chipGroupCategory.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Kategori: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Modul 9 Tahap 4: Hubungkan tombol grid ke QuizSessionActivity
        binding.btnQuiz1.setOnClickListener { openQuizSession("Grammar 1") }
        binding.btnQuiz2.setOnClickListener { openQuizSession("Kosakata 1") }
        binding.btnQuiz3.setOnClickListener { openQuizSession("Tulis Kana") }
        binding.btnQuiz4.setOnClickListener { openQuizSession("Listening") }
        binding.btnQuiz5.setOnClickListener { openQuizSession("Percakapan") }
        binding.btnQuiz6.setOnClickListener { openQuizSession("Review Akhir") }
    }

    private fun openQuizSession(quizTitle: String) {
        val intent = Intent(requireContext(), QuizSessionActivity::class.java)
        intent.putExtra("QUIZ_TITLE", quizTitle)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
