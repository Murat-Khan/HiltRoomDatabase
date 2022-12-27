package com.murat.hiltroomdatabase.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.murat.hiltroomdatabase.utils.Constants
import com.murat.hiltroomdatabase.utils.Constants.NOTE_TABLE

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity)

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE ORDER BY noteId DESC")
    fun getAllNotes():MutableList<NoteEntity>

    @Query("SELECT * FROM $NOTE_TABLE WHERE noteId like :id")
    fun getNote(id : Int) :NoteEntity
}