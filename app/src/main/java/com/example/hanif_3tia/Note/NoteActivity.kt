package com.example.hanif_3tia.Note

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hanif_3tia.data.AppDatabase
import com.example.hanif_3tia.data.entity.NoteEntity
import com.example.hanif_3tia.databinding.ActivityNoteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Modul 12: Halaman daftar Catatan Belajar Room Database
class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private lateinit var db: AppDatabase
    private lateinit var adapter: NoteAdapter
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar kembali
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Modul 12 Tahap 06: Inisialisasi Database Room Singleton
        db = AppDatabase.getInstance(this)

        // Modul 12 Tahap 07: Inisialisasi Adapter dengan callback hapus
        adapter = NoteAdapter(notes) { note ->
            deleteNote(note)
        }

        // Setup RecyclerView
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = adapter

        // Modul 12 Tahap 05: FAB membuka NoteFormActivity
        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(this, NoteFormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    // Modul 12 Tahap 07: Mengambil data terbaru dari Room via Coroutine
    private fun fetchNotes() {
        lifecycleScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    db.noteDao().getAll()
                }
                notes.clear()
                notes.addAll(result)
                adapter.notifyDataSetChanged()

                // Toggle empty state
                if (notes.isEmpty()) {
                    binding.layoutEmpty.visibility = View.VISIBLE
                    binding.rvNotes.visibility = View.GONE
                } else {
                    binding.layoutEmpty.visibility = View.GONE
                    binding.rvNotes.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                Toast.makeText(this@NoteActivity, "Gagal memuat catatan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Modul 12 Tahap 07: Menghapus catatan dari Room via Coroutine
    private fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    db.noteDao().delete(note)
                }
                Toast.makeText(this@NoteActivity, "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show()
                fetchNotes()
            } catch (e: Exception) {
                Toast.makeText(this@NoteActivity, "Gagal menghapus catatan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
