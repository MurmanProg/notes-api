package ru.notes.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.notes.api.model.entity.Note
import ru.notes.api.service.NoteService


/**
 * @author Created on 14.03.2021
 */
@RestController
@RequestMapping("/notes")
class NoteController {
    @Autowired
    private lateinit var service: NoteService

    @PostMapping(value = [""])
    fun save(@RequestBody note: Note): ResponseEntity<Note> {
        val ourNote = service.createNote(note.title, note.content)
        return if (ourNote.id!! > 0L) ResponseEntity<Note>(
            ourNote,
            HttpStatus.CREATED
        ) else ResponseEntity<Note>(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping(value = [""])
    fun getAll(): ResponseEntity<List<Note>> {
        val ourNotes = service.getAll()
        return if (ourNotes.size > 0L) ResponseEntity<List<Note>>(
            ourNotes,
            HttpStatus.OK
        ) else ResponseEntity<List<Note>>(HttpStatus.NOT_FOUND)
    }

    @GetMapping(value = ["/{id}"])
    fun getById(@PathVariable id: Long): ResponseEntity<Note> {
        val ourNote = service.getById(id)
        return if (ourNote != null) ResponseEntity<Note>(
            ourNote,
            HttpStatus.OK
        ) else ResponseEntity<Note>(HttpStatus.NOT_FOUND)
    }

    /** @GetMapping()
    fun getByQuery(@RequestParam("query") query : String): ResponseEntity<List<Note>> {
    val ourNotes = service.getByQuery(query)
    return if (ourNotes.size > 0L) ResponseEntity<List<Note>>(
    ourNotes,
    HttpStatus.OK
    ) else ResponseEntity<List<Note>>(HttpStatus.NOT_FOUND)
    }*/

    @DeleteMapping(value = ["/{id}"])
    fun deleteById(@PathVariable id: Long): ResponseEntity<String> {
        if (service.getById(id).id == null)
            return ResponseEntity<String>(HttpStatus.NOT_FOUND)
        service.deleteNote(id)
        return if (service.getById(id).id == null) ResponseEntity<String>(
            HttpStatus.OK
        ) else ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping(value = ["/{id}"])
    fun updateById(@PathVariable id: Long, @RequestBody note: Note): ResponseEntity<Note> {
        if (service.getById(id).id == null) {
            return ResponseEntity<Note>(HttpStatus.NOT_FOUND)
        } else
            return ResponseEntity<Note>(
                service.updateNote(id, note.title, note.content),
                HttpStatus.OK
            )
    }


}