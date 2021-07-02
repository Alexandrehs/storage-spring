package com.chaveiro.storagespring.entities

import com.chaveiro.storagespring.rest.BrandsRequest
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "brands")
data class BrandsEntity (

    @Id
    val id: String?,

    @Column(name = "name")
    val name: String?,

    @Column(name = "type")
    val type: String?
) {
    constructor(brandsRequest: BrandsRequest) : this (
        id = UUID.randomUUID().toString(),
        name = brandsRequest.name,
        type = brandsRequest.type
    )
}