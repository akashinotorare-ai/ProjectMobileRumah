package com.example.hanif_3tia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hanif_3tia.data.entity.NoteEntity

// Modul 12 Tahap 05: Interface DAO untuk operasi CRUD SQLite via Room
@Dao
interface NoteDao {
    @Query("SELECT * FROM study_notes ORDER BY createdAt DESC")
    suspend fun getAll(): List<NoteEntity>

    @Insert
    suspend fun insert(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}
