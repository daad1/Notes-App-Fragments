package com.example.notesappfragments

import androidx.lifecycle.LiveData

class Repository (private val dao:NoteDao) {
    suspend fun addNote(note: Note){
        dao.addNote(note)
    }

    fun  getAllNotes(): LiveData<List<Note>> = dao.getAllNote()

    suspend fun updateNote(note: Note){
        dao.updateNote(note)
    }


    suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    suspend fun deleteAllNote(){
        dao.deleteAllNote()
    }
}
