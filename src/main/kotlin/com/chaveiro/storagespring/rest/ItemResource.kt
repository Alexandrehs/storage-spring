package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.ItemsEntity
import com.chaveiro.storagespring.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

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

    @PutMapping("/update")
    fun updateStorageItem(@PathParam(value = "id") id : String , @PathParam(value = "newStorage") newStorage: String) {
        val updatedItem = repository.findById(id)
        updatedItem.map {
            val item : ItemsEntity = it.copy(
                name = it.name,
                price = it.price,
                storage = newStorage,
                brandId = it.brandId,
                minimum = it.minimum
            )
            print(item)
            repository.save(item)
        }
    }
}