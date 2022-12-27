package com.murat.hiltroomdatabase.repository

import com.murat.hiltroomdatabase.db.NoteDao
import com.murat.hiltroomdatabase.db.NoteEntity
import javax.inject.Inject

class DbRepository
@Inject constructor(
    private val dao :NoteDao
) {
    fun saveNote(note: NoteEntity) = dao.insertNote(note)
    fun updateNote(note: NoteEntity) = dao.updateNote(note)
    fun deleteNote(note: NoteEntity) = dao.deleteNote(note)
    fun getNote(id : Int) = dao.getNote(id)
    fun getAllNote() = dao.getAllNotes()
}