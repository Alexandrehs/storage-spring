package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.BrandsEntity
import com.chaveiro.storagespring.entities.ItemsEntity
import com.sun.istack.NotNull

data class ItemRequest(

    @field:NotNull
    val name: String?,

    @field:NotNull
    val price: String?,

    @field:NotNull
    val storage: String?,

    @field:NotNull
    val minimum: String?,

    @field:NotNull
    val brandId: String?
)

data class ItemResponse(
    val id: String?
) {
    constructor(item: ItemsEntity) : this (
        id = item.id
    )
}

data class BrandsRequest (

    @field:NotNull
    val id: String?,

    @field:NotNull
    val name: String?
)

data class BrandsResponse (
    val id: String?
){
    constructor(brand: BrandsEntity) : this (
        id = brand.id
    )
}