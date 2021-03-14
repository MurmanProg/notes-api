package ru.notes.api.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.notes.api.model.entity.Note

/**
 * @author Created on 14.03.2021
 */
@Repository
interface NoteRepo : JpaRepository<Note, Long> {
    fun getAllByContentContainsOrTitleContains(content: String, title: String): List<Note>
}