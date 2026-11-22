package com.example.hanif_3tia.More

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.hanif_3tia.LoginActivity
import com.example.hanif_3tia.Note.NoteActivity
import com.example.hanif_3tia.TutorialScreenActivity
import com.example.hanif_3tia.WebViewActivity
import com.example.hanif_3tia.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Modul 6 Latihan 5.1 & Modul 7 Latihan 5.1: Sapaan Pengguna dari SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna") ?: "Pengguna"
        val email = sharedPref.getString("email", "$username@gmail.com")
        val activeLang = sharedPref.getString("selected_language", "English")

        binding.tvWelcomeUser.text = "Halo, $username!"
        binding.tvUserEmail.text = email
        binding.tvActiveLang.text = "Fokus: $activeLang"

        // Modul 12: Buka Catatan Belajar Bangbo (Room Database)
        binding.btnOpenNotes.setOnClickListener {
            startActivity(Intent(requireContext(), NoteActivity::class.java))
        }

        // Modul 5 Tahap 5: Buka WebViewActivity saat tombol diklik
        binding.btnOpenWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Modul 6 Tahap 1.3 & Modul 7 Tahap 2.2: Tombol Logout dengan AlertDialog Konfirmasi
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari akun Bangbo?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus sesi dari SharedPreferences
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()

                    // Kembali ke halaman awal Onboarding/TutorialScreenActivity dengan membersihkan back stack
                    val intent = Intent(requireContext(), TutorialScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
