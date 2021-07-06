package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.ItemsEntity
import com.chaveiro.storagespring.entities.RecordsEntity
import com.chaveiro.storagespring.repository.ItemRepository
import com.chaveiro.storagespring.repository.RecordsRepository
import com.chaveiro.storagespring.services.ItemsServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/items")
class ItemResource {

    @Autowired
    lateinit var repository: ItemRepository

    @Autowired
    lateinit var itemsServices: ItemsServices

    @Autowired
    lateinit var recordsRepository: RecordsRepository

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
    fun updateStorageItem(
        @PathVariable("id") id : String,
        @RequestBody itemRequest: ItemRequest,
        @RequestParam type: String
    )
    : ResponseEntity<String> {
        val updatedItem = repository.findById(id)
        var idItemUpdated: String = ""
        updatedItem.map {
            val item = it.copy(
                name = it.name,
                price = it.price,
                storage = itemRequest.storage!!,
                brandid = it.brandid,
                minimum = it.minimum
            )
            val record = RecordsEntity(
                id = UUID.randomUUID().toString(),
                name = it.name,
                registeredIn = LocalDate.now(),
                total = (itemRequest.storage!!.toInt() * it.price.toInt()).toString(),
                price = it.price,
                item_id = id,
                theAmount = itemRequest.recordTheAmount!!,
                type = type
            )
            idItemUpdated = repository.save(item).id
            recordsRepository.save(record)
        }

        return ResponseEntity.status(HttpStatus.OK).body(idItemUpdated)
    }
}