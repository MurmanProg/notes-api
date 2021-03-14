package ru.notes.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.notes.api.model.entity.Note
import ru.notes.api.model.repository.NoteRepo

/**
 * @author Created  on 14.03.2021
 */
@Service
class NoteServiceImpl : NoteService {
    @Autowired
    private lateinit var noteRepo: NoteRepo

    @Value("\${N}")
    private val N: Int? = null

    override fun createNote(title: String?, content: String?): Note = noteRepo.save(Note().apply {
        this.title = title
        this.content = content
    })

    override fun getAll(): List<Note> = noteRepo.findAll().map { pretifyNote(it) }

    override fun getById(id: Long): Note = pretifyNote(noteRepo.getOne(id))

    override fun updateNote(id: Long, title: String?, content: String?): Note = noteRepo.save(Note().apply {
        this.id = id
        this.title = title
        this.content = content
    })

    override fun getByQuery(query: String): List<Note> {
        return noteRepo.getAllByContentContainsOrTitleContains(query, query).map { pretifyNote(it) }
    }

    override fun deleteNote(id: Long) {
        noteRepo.deleteById(id)
    }

    private fun pretifyNote(note: Note): Note {
        if (note.title.isNullOrEmpty()) {
            if (note.content!!.length <= N!!) {
                note.title = note.content!!
            } else {
                note.title = note.content!!.substring(0, N)
            }
        }
        return note
    }
}