package ru.notes.api.service

import ru.notes.api.model.entity.Note

/**
 * @author Created on 14.03.2021
 */
interface NoteService {
    fun createNote(title: String?, content: String?): Note

    fun getAll(): List<Note>

    fun getById(id: Long): Note

    fun updateNote(id: Long, title: String?, content: String?): Note

    fun getByQuery(query: String): List<Note>

    fun deleteNote(id: Long)
}