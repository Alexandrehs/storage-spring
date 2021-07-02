package com.chaveiro.storagespring.entities

import com.chaveiro.storagespring.rest.YalesRequest
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "yales")
data class YalesEntity (
    @Id
    val id: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "brandid")
    val brandid: String,

    @Column(name = "storage")
    val storage: String,

    @Column(name = "price")
    val price: String,

    @Column(name = "minimum")
    val minimum: String
) {
    constructor(yalesRequest: YalesRequest) : this (
        id = UUID.randomUUID().toString(),
        name = yalesRequest.name!!,
        brandid = yalesRequest.brandid!!,
        storage = yalesRequest.storage!!,
        price = yalesRequest.price!!,
        minimum= yalesRequest.minimum!!
    )
}