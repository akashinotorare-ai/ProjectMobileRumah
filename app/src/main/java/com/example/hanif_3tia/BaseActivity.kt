package com.example.hanif_3tia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.hanif_3tia.Home.HomeFragment
import com.example.hanif_3tia.Module.ModuleFragment
import com.example.hanif_3tia.More.MoreFragment
import com.example.hanif_3tia.Quiz.QuizFragment
import com.example.hanif_3tia.Target.TargetFragment
import com.example.hanif_3tia.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Modul 7 Tahap 1.4: Mengatur insets bawah = 0 agar Bottom Navigation tidak ada jarak kosong
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Tampilkan HomeFragment secara default (Modul 7 Tahap 1.5)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Navigasi 5 Tab Bottom Navigation (Modul 7)
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_quiz -> {
                    replaceFragment(QuizFragment())
                    true
                }
                R.id.nav_dictionary -> {
                    replaceFragment(ModuleFragment())
                    true
                }
                R.id.nav_target -> {
                    replaceFragment(TargetFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(MoreFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Modul 6 Tahap 4.4 & Modul 7 Tahap 1.5: Fungsi Mengganti Isi Fragment Container
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}
