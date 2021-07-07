package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.BrandsEntity
import com.chaveiro.storagespring.repository.BrandsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping
    fun getAllBrands() : ResponseEntity<List<BrandsEntity>> {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll())
    }

    @GetMapping("/{type}")
    fun getBrandsByTypes(@PathVariable("type") type: String) : ResponseEntity<List<BrandsEntity>> {
        val brands = repository.findAllByType(type)
        return ResponseEntity.status(HttpStatus.OK).body(brands)
    }
}