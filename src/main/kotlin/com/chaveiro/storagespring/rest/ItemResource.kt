package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.ItemsEntity
import com.chaveiro.storagespring.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/items")
class ItemResource {

    @Autowired
    lateinit var repository: ItemRepository

    @PostMapping
    fun itemCreate(@RequestBody itemRequest: ItemRequest) : ResponseEntity<String> {
        print(itemRequest)
        val item = repository.save(ItemsEntity(itemRequest))
        return ResponseEntity.status(HttpStatus.CREATED).body(item.id)
    }
}