package com.chaveiro.storagespring.repository

import com.chaveiro.storagespring.entities.YalesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface YalesRepository : JpaRepository<YalesEntity, String> {

    @Query("select * from yales order by name asc", nativeQuery = true)
    override fun findAll(): List<YalesEntity>

    @Query("select " +
            "   y.id," +
            "   y.name," +
            "   y.storage," +
            "   y.price," +
            "   y.minimum," +
            "   y.brandid" +
            "   from yales y" +
            "   inner join brands b" +
            "   on y.brandid = b.id" +
            "   where b.id = :id"+
            "   and b.type = '2'" +
            "   order by y.name;", nativeQuery = true)
    public fun getYaleByBrand(@Param("id") id: String?): List<YalesEntity>
}