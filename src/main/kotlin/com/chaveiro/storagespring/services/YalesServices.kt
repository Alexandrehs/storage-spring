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
            yaleUpdate.map {
                val yale = it.copy(
                    name = it.name,
                    price = it.price,
                    storage = yalesRequest.storage!!,
                    brandid = it.brandid,
                    minimum = it.minimum
                )
                val record = RecordsEntity(
                    id = UUID.randomUUID().toString(),
                    name = it.name,
                    registeredIn = LocalDate.now(),
                    total = (yalesRequest.storage!!.toInt() * it.price.toInt()).toString(),
                    price = it.price,
                    item_id = id,
                    theAmount = yalesRequest.storage,
                    type = type
                )

                repository.save(yale)
                recordsRepository.save(record)
            }
        } catch (error : Exception) {
            throw error
        }
    }
}