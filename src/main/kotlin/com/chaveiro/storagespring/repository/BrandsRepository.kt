package com.chaveiro.storagespring.repository

import com.chaveiro.storagespring.entities.BrandsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BrandsRepository : JpaRepository<BrandsEntity, String> {

    @Query("select * from brands where type = :type order by name asc", nativeQuery = true)
    fun findAllByType(@Param("type") type: String): List<BrandsEntity>
}