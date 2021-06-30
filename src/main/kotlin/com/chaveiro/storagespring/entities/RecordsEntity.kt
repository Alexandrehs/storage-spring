package com.chaveiro.storagespring.entities

import java.time.LocalDate
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "records")
data class RecordsEntity (
    @Id
    val id: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "registeredin")
    val registeredIn: LocalDate,

    @Column(name = "total")
    val total: String,

    @Column(name = "theamount")
    val theAmount: String,

    @Column(name = "price")
    val price: String,

    @Column(name = "item_id")
    val item_id: String,

    @Column(name = "type")
    val type: String
)