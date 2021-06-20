package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.ItemsEntity
import com.chaveiro.storagespring.repository.ItemRepository
import com.chaveiro.storagespring.services.ItemsServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class ItemResource {

    @Autowired
    lateinit var repository: ItemRepository

    @Autowired
    lateinit var itemsServices: ItemsServices

    @PostMapping
    fun createItem(@RequestBody itemRequest: ItemRequest) : ResponseEntity<String> {
        val item = repository.save(ItemsEntity(itemRequest))
        return ResponseEntity.status(HttpStatus.CREATED).body(item.id)
    }

    @GetMapping
    fun getItems() : ResponseEntity<List<ItemsEntity>> {
        val items = repository.findAll(Sort.by("name").ascending())
        return ResponseEntity.status(HttpStatus.OK).body(items)
    }

    @GetMapping("/brand/{id}")
    fun getItemsByBrands(@PathVariable("id") brandId: String) : ResponseEntity<List<ItemsEntity>> {
        val itemsByBrands = itemsServices.getItemsById(brandId)

        return ResponseEntity.status(HttpStatus.OK).body(itemsByBrands)
    }

    @PutMapping("/{id}")
    fun updateStorageItem(@PathVariable("id") id : String, @RequestBody itemRequest: ItemRequest) : ResponseEntity<String> {
        val updatedItem = repository.findById(id)
        var idItemUpdated: String = ""
        updatedItem.map {
            val item : ItemsEntity = it.copy(
                name = it.name,
                price = it.price,
                storage = itemRequest.storage!!,
                brandid = it.brandid,
                minimum = it.minimum
            )
            idItemUpdated = repository.save(item).id
        }
        return ResponseEntity.status(HttpStatus.OK).body(idItemUpdated)
    }
}