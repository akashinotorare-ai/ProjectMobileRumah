package com.example.hanif_3tia.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Modul 12 Tahap 04: Definisi Entity tabel dengan 5 atribut (syarat minimal 4 atribut)
@Entity(tableName = "study_notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val category: String,
    val createdAt: Long = System.currentTimeMillis()
)
