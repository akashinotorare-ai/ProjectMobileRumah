package com.example.hanif_3tia

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hanif_3tia.Module.Tutorial.Tutorial1Fragment
import com.example.hanif_3tia.Module.Tutorial.Tutorial2Fragment
import com.example.hanif_3tia.Module.Tutorial.Tutorial3Fragment
import com.example.hanif_3tia.Module.Tutorial.TutorialFragmentAdapter
import com.example.hanif_3tia.databinding.ActivityTutorialScreenBinding

class TutorialScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTutorialScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Modul 9 Tahap 5: Setup ViewPager2 dengan Adapter 3 Fragment
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialModuleViewPager.adapter = adapter

        // Modul 9 Tahap 6: DotsIndicator dipasang setelah adapter
        binding.dotIndicator.attachTo(binding.tutorialModuleViewPager)

        // Grand Design Bangbo: Tombol Aksi
        binding.btnMulaiSekarang.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnSudahPunyaAkun.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
