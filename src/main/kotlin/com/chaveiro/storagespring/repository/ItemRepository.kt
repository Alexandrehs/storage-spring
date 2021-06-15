package com.chaveiro.storagespring.repository

import com.chaveiro.storagespring.entities.ItemsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<ItemsEntity, String> {
}