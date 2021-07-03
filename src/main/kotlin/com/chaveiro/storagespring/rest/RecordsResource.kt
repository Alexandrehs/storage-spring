package com.chaveiro.storagespring.rest

import com.chaveiro.storagespring.entities.RecordsEntity
import com.chaveiro.storagespring.repository.IRecordsOrderByType
import com.chaveiro.storagespring.repository.RecordsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/records")
class RecordsResource {

    @Autowired
    lateinit var recordsRepository: RecordsRepository

    @GetMapping
    fun getRecords(): ResponseEntity<List<RecordsEntity>> {
        val records = recordsRepository.getRecords()

        return ResponseEntity.status(HttpStatus.OK).body(records)
    }

    @GetMapping("/entrance")
    fun getRecordsWhereEntrance(): ResponseEntity<List<RecordsEntity>> {
        val recorsWhereEntrance = recordsRepository.getRecordsWhereTypeEntrance()

        return ResponseEntity.status(HttpStatus.OK).body(recorsWhereEntrance)
    }

    @GetMapping("/exit")
    fun getRecordsWhereExit(): ResponseEntity<List<RecordsEntity>> {
        val recorsWhereExit = recordsRepository.getRecordsWhereTypeExit()

        return ResponseEntity.status(HttpStatus.OK).body(recorsWhereExit)
    }

    @GetMapping("/filter")
    fun getRecordsGroupByType(@PathParam("type") type: String) : ResponseEntity<List<IRecordsOrderByType>> {
        return ResponseEntity.status(HttpStatus.OK).body(recordsRepository.getRecordsGroupBytype(type))
    }
}