package com.chaveiro.storagespring.services

import com.chaveiro.storagespring.entities.ItemsEntity
import com.chaveiro.storagespring.repository.ItemRepository
import com.chaveiro.storagespring.rest.ItemsResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemsServices {
    @Autowired
    lateinit var repository: ItemRepository

    fun getItemsById(brandId: String?) : List<ItemsEntity> {
        return repository.getItemsByBrand(brandId)
    }
}