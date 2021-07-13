package com.chaveiro.storagespring.services

import com.chaveiro.storagespring.entities.RecordsEntity
import com.chaveiro.storagespring.entities.YalesEntity
import com.chaveiro.storagespring.repository.RecordsRepository
import com.chaveiro.storagespring.repository.YalesRepository
import com.chaveiro.storagespring.rest.YalesRequest
import com.chaveiro.storagespring.rest.YalesResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class YalesServices {

    @Autowired
    lateinit var repository: YalesRepository

    @Autowired
    lateinit var recordsRepository: RecordsRepository

    public fun createYale(yalesRequest: YalesRequest) : YalesResponse {
        val yale = YalesEntity(yalesRequest)
        return YalesResponse(repository.save(yale))
    }

    public fun getAllYale(): List<YalesResponse> {
        val yales = repository.findAll(Sort.by("name").ascending())
        return yales.map {
            YalesResponse(it)
        }
    }

    public fun getYaleByBrand(id: String) : List<YalesResponse> {
        val yales = repository.getYaleByBrand(id)
        return yales.map {
            YalesResponse(it)
        }
    }

    public fun updateYale(id: String, yalesRequest: YalesRequest, type: String) {
        try {
            val yaleUpdate = repository.findById(id)
            var nameEdited = ""
            var priceEdited = "0"

            yaleUpdate.map {
                nameEdited = it.name
                priceEdited = it.price

                if(!yalesRequest.name.isNullOrEmpty()) {
                    if(yalesRequest.name !== it.name) {
                        nameEdited = yalesRequest.name
                    }
                }

                if(!yalesRequest.price.isNullOrEmpty()) {
                    if(yalesRequest.price !== it.price) {
                        priceEdited = yalesRequest.price
                    }
                }

                val yale = it.copy(
                    name = nameEdited,
                    price = priceEdited,
                    storage = yalesRequest.storage!!,
                    brand_id = it.brand_id,
                    minimum = it.minimum
                )

                if(!yalesRequest.recordTheAmount.isNullOrEmpty()) {
                    val record = RecordsEntity(
                        id = UUID.randomUUID().toString(),
                        name = it.name,
                        registeredIn = LocalDate.now(),
                        total = (yalesRequest.recordTheAmount!!.toInt() * it.price.toInt()).toString(),
                        price = it.price,
                        item_id = id,
                        theAmount = yalesRequest.recordTheAmount,
                        type = type
                    )

                    recordsRepository.save(record)
                }

                repository.save(yale)
            }
        } catch (error : Exception) {
            throw error
        }
    }
}