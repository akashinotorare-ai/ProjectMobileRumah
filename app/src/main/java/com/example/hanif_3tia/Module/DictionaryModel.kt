package com.example.hanif_3tia.Module

// Modul 10 Tahap 4.2: Data class model untuk item RecyclerView Kamus
data class DictionaryModel(
    val word: String,
    val phonetic: String,
    val meaning: String,
    val example: String,
    val partOfSpeech: String
)
