package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.ItemsEntity
import com.chaveiro.storagespring.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class ItemResource {

    @Autowired
    lateinit var repository: ItemRepository

    @PostMapping
    fun createItem(@RequestBody itemRequest: ItemRequest) : ResponseEntity<String> {
        val item = repository.save(ItemsEntity(itemRequest))
        return ResponseEntity.status(HttpStatus.CREATED).body(item.id)
    }

    @GetMapping
    fun getItems() : ResponseEntity<List<ItemsEntity>> {
        val items = repository.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(items)
    }

    @PutMapping("/{id}")
    fun updateStorageItem(@PathVariable("id") id : String, @RequestBody itemRequest: ItemRequest) : ResponseEntity<ItemResponse> {
        val updatedItem = repository.findById(id)
        var idItemUpdated : String = ""
        updatedItem.map {
            val item : ItemsEntity = it.copy(
                name = it.name,
                price = it.price,
                storage = itemRequest.storage!!,
                brandId = it.brandId,
                minimum = it.minimum
            )
            idItemUpdated = repository.save(item).id
        }
        return ResponseEntity.status(HttpStatus.OK).body(ItemResponse(idItemUpdated))
    }
}