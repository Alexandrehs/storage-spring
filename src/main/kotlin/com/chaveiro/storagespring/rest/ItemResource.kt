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
import javax.validation.Valid

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
    fun createItem(@RequestBody @Valid itemRequest: ItemRequest) : ResponseEntity<String> {
        return try {
            val item = repository.save(ItemsEntity(itemRequest))
            ResponseEntity.status(HttpStatus.CREATED).body(item.id)
        } catch (error: Exception) {
            throw error
        }
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
        var priceEdited: String = "0"
        var nameEdited: String = ""

        updatedItem.map {
            priceEdited = it.price
            nameEdited = it.name

            if(!itemRequest.price.isNullOrEmpty()) {
                if(itemRequest.price !== it.price) {
                    priceEdited = itemRequest.price
                }
            }

            if(!itemRequest.name.isNullOrEmpty()) {
                if(itemRequest.name !== it.name) {
                    nameEdited = itemRequest.name
                }
            }

            val item = it.copy(
                name = nameEdited.uppercase(),
                price = priceEdited,
                storage = itemRequest.storage!!,
                brand_id = it.brand_id,
                minimum = it.minimum
            )

            if(!itemRequest.recordTheAmount.isNullOrEmpty()) {
                val record = RecordsEntity(
                    id = UUID.randomUUID().toString(),
                    name = nameEdited.uppercase(),
                    registeredIn = LocalDate.now(),
                    total = (itemRequest.recordTheAmount!!.toInt() * priceEdited.toInt()).toString(),
                    price = priceEdited,
                    item_id = id,
                    theAmount = itemRequest.recordTheAmount!!,
                    type = type
                )
                recordsRepository.save(record)
            }
            idItemUpdated = repository.save(item).id
        }

        return ResponseEntity.status(HttpStatus.OK).body(idItemUpdated)
    }
}