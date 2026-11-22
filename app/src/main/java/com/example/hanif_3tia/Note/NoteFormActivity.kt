package com.example.hanif_3tia.Note

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.hanif_3tia.data.AppDatabase
import com.example.hanif_3tia.data.entity.NoteEntity
import com.example.hanif_3tia.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

// Modul 12 Tahap 06: Form input dan penyimpanan catatan ke Room Database
class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Modul 12 Tahap 06: Inisialisasi Database
        db = AppDatabase.getInstance(this)

        // Modul 12 Tahap 06: Aksi Simpan
        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val category = binding.etCategory.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            // Validasi form (Modul 12 Tahap 06)
            if (title.isBlank() || content.isBlank() || category.isBlank()) {
                Toast.makeText(this, "Isi semua kolom form!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.btnSaveNote.isEnabled = false

            // Modul 12 Tahap 06: Menyimpan ke SQLite Room via Coroutine
            lifecycleScope.launch {
                try {
                    db.noteDao().insert(
                        NoteEntity(
                            title = title,
                            content = content,
                            category = category,
                            createdAt = System.currentTimeMillis()
                        )
                    )
                    Toast.makeText(this@NoteFormActivity, "Catatan berhasil disimpan ke Room!", Toast.LENGTH_SHORT).show()
                    finish()
                } catch (e: Exception) {
                    binding.btnSaveNote.isEnabled = true
                    Toast.makeText(
                        this@NoteFormActivity,
                        "Gagal menyimpan catatan: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
