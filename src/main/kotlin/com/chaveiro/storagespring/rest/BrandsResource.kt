package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.BrandsEntity
import com.chaveiro.storagespring.repository.BrandsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/brands")
class BrandsResource {

    @Autowired
    lateinit var repository: BrandsRepository

    @PostMapping()
    fun createBrand(@RequestBody brandsRequest: BrandsRequest) : ResponseEntity<String> {
        val brand = repository.save(BrandsEntity(brandsRequest))
        return ResponseEntity.status(HttpStatus.CREATED).body(brand.id)
    }
}