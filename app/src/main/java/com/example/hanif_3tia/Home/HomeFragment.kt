package com.example.hanif_3tia.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.hanif_3tia.DetailActivity
import com.example.hanif_3tia.Quiz.QuizSessionActivity
import com.example.hanif_3tia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // Update data XP saat kembali dari kuis
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val userXp = sharedPref.getInt("user_xp", 1420)
        binding.tvXp.text = "%,d XP".format(userXp)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val selectedLang = sharedPref.getString("selected_language", "English")
        val userXp = sharedPref.getInt("user_xp", 1420)

        // Tampilkan info bahasa di bab dan XP
        binding.chipBab.text = "BAB 1 • $selectedLang"
        binding.tvXp.text = "%,d XP".format(userXp)

        // Klik Node 1 (Tata Bahasa - Selesai)
        binding.node1.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Tata Bahasa (Selesai)")
                .setMessage("Kamu telah menyelesaikan modul Tata Bahasa dengan nilai sempurna! Ingin mereview kembali materi?")
                .setPositiveButton("Buka Review") { _, _ ->
                    startActivity(Intent(requireContext(), DetailActivity::class.java))
                }
                .setNegativeButton("Tutup") { d, _ -> d.dismiss() }
                .show()
        }

        // Klik Node 2 (Kosakata - Selesai)
        binding.node2.setOnClickListener {
            Toast.makeText(requireContext(), "Kosakata: Sudah dipelajari (100%)", Toast.LENGTH_SHORT).show()
        }

        // Klik Node 3 (Perkenalan - AKTIF / START!) -> Buka QuizSessionActivity
        binding.node3.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Mulai: Perkenalan Diri")
                .setMessage("Sesi belajar interaktif 3 soal. Raih +10 XP per soal dan pertahankan streak belajarmu!")
                .setPositiveButton("Mulai Belajar Sekarang") { _, _ ->
                    val intent = Intent(requireContext(), QuizSessionActivity::class.java)
                    intent.putExtra("MODULE_NAME", "Perkenalan")
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { d, _ -> d.dismiss() }
                .show()
        }

        // Klik Node 4 & 5 (Terkunci)
        binding.node4.setOnClickListener {
            Toast.makeText(requireContext(), "🔒 Selesaikan materi 'Perkenalan' terlebih dahulu!", Toast.LENGTH_SHORT).show()
        }

        binding.node5.setOnClickListener {
            Toast.makeText(requireContext(), "🔒 Materi masih terkunci!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
