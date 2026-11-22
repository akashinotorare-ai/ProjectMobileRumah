package com.example.hanif_3tia.Note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hanif_3tia.data.entity.NoteEntity
import com.example.hanif_3tia.databinding.ItemNoteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Modul 12 Tahap 07: Adapter RecyclerView untuk daftar catatan Room
class NoteAdapter(
    private val notes: List<NoteEntity>,
    private val onDelete: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        with(holder.binding) {
            tvTitle.text = note.title
            tvContent.text = note.content
            tvCategory.text = note.category

            val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            tvDate.text = "Dibuat: ${sdf.format(Date(note.createdAt))}"

            // Modul 12 Tahap 07: Dialog konfirmasi MaterialAlertDialogBuilder sebelum menghapus
            btnDelete.setOnClickListener {
                MaterialAlertDialogBuilder(holder.itemView.context)
                    .setTitle("Hapus Catatan")
                    .setMessage("Yakin ingin menghapus catatan \"${note.title}\"? Data akan dihapus permanen dari Room Database.")
                    .setPositiveButton("Ya") { _, _ -> onDelete(note) }
                    .setNegativeButton("Batal", null)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = notes.size
}
