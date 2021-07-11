package com.chaveiro.storagespring.entities

import com.chaveiro.storagespring.rest.ItemRequest
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "items")
data class ItemsEntity (

    @Id
    val id: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "price")
    val price: String,

    @Column(name = "storage")
    val storage: String,

    @Column(name = "minimum")
    val minimum: String,

    @Column(name = "brandid")
    val brand_id : String,
) {
    constructor(itemRequest: ItemRequest) : this(
        id = UUID.randomUUID().toString(),
        name = itemRequest.name!!.uppercase(),
        price = itemRequest.price!!,
        storage = itemRequest.storage!!,
        minimum = itemRequest.minimum!!,
        brand_id = itemRequest.brand_id!!,
    )
}