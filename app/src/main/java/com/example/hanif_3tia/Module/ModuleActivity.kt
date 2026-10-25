package com.example.hanif_3tia.Module

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hanif_3tia.R
import com.example.hanif_3tia.databinding.ActivityModuleBinding

class ModuleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModuleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityModuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sender = intent.getStringExtra("SENDER")
        val message = intent.getStringExtra("MESSAGE")

        binding.sender.text = sender
        binding.message.text = message
    }
}