package com.chaveiro.storagespring.repository

import com.chaveiro.storagespring.entities.ItemsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<ItemsEntity, String> {

    @Query("select i.id, i.name, i.price, i.storage, i.minimum, b.name as brandid " +
            "from ITEMS i inner join BRANDS b on i.brandid = b.id where i.brandid = :id " +
            "order by i.name asc", nativeQuery = true)
    public fun getItemsByBrand(@Param("id") id: String?) : List<ItemsEntity>
}