package com.example.hanif_3tia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hanif_3tia.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM study_notes ORDER BY createdAt DESC")
    fun getAll(): List<NoteEntity>

    @Insert
    fun insert(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)
}
