package com.example.hanif_3tia.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hanif_3tia.DetailActivity
import com.example.hanif_3tia.LoginActivity
import com.example.hanif_3tia.R
import com.example.hanif_3tia.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"


        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val user = sharedPref.getString("username", "User")

        binding.tvUsername.text = user

        binding.btnLogOut.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Log Out")
                .setMessage("Apakah anda ingin keluar dari akun anda saat ini ?")
                .setNegativeButton("Ya",) { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
                .setPositiveButton("Tidak") { dialog, _ ->

                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()

        }

        binding.btnCoursePengenalanPython.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }
    }
}