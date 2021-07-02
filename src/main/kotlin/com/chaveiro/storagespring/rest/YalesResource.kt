package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.services.YalesServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/yales")
class YalesResource {

    @Autowired
    lateinit var yalesServices: YalesServices

    @PostMapping
    fun createYales(@RequestBody yalesRequest: YalesRequest) : ResponseEntity<YalesResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(yalesServices.createYale(yalesRequest))
    }

    @GetMapping
    fun getAllYales() : ResponseEntity<List<YalesResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(yalesServices.getAllYale())
    }

    @GetMapping("/brand/{id}")
    fun getYalesByBrands(@PathVariable("id") id: String) : ResponseEntity<List<YalesResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(yalesServices.getYaleByBrand(id))
    }

    @PutMapping("/{id}")
    fun updateYale(
        @PathVariable("id") id: String,
        @RequestBody yalesRequest: YalesRequest,
        @RequestParam type: String) {

        yalesServices.updateYale(id, yalesRequest, type)
    }
}