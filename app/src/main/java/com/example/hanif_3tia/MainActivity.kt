package com.example.hanif_3tia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hanif_3tia.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val user = sharedPref.getString("username", "")

        binding.tvUsername.text = user



        binding.btnLogOut.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Log Out")
                .setMessage("Apakah anda ingin keluar dari akun anda saat ini ?")
                .setNegativeButton("Ya", ){dialog, _->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setPositiveButton("Tidak") {dialog, _->

                        dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        binding.btnCoursePengenalanPython.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }
}