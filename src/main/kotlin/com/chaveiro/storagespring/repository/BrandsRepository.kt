package com.chaveiro.storagespring.repository

import com.chaveiro.storagespring.entities.BrandsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandsRepository : JpaRepository<BrandsEntity, String> {
}