package com.chaveiro.storagespring.services

import com.chaveiro.storagespring.entities.ItemsEntity
import com.chaveiro.storagespring.repository.ItemRepository
import com.chaveiro.storagespring.rest.ItemRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ItemsServices {
    @Autowired
    lateinit var repository: ItemRepository

    fun createItem(itemRequest: ItemRequest) : ResponseEntity<String> {
        return try {
            repository.save(ItemsEntity(itemRequest))
            ResponseEntity.status(HttpStatus.CREATED).body("Ok")
        } catch (erro: Exception) {
            return throw erro
        }
    }

    fun getItemsById(brandId: String?) : List<ItemsEntity> {
        return repository.getItemsByBrand(brandId)
    }
}