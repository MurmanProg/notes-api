package ru.notes.api.model.entity

import javax.persistence.*

/**
 * @author Created on 14.03.2021
 */
@Entity
@Table(name = "notes")
data class Note(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "title", columnDefinition = "text", nullable = true)
    var title: String? = null,

    @Column(name = "content", columnDefinition = "text", nullable = false)
    var content: String? = null,
)
