package com.chaveiro.storagespring.repository

import com.chaveiro.storagespring.entities.RecordsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RecordsRepository : JpaRepository<RecordsEntity, String> {
    @Query("select" +
            "   id," +
            "   name," +
            "   registeredin," +
            "   total," +
            "   price," +
            "   item_id," +
            "   theamount," +
            "   type" +
            "   from records;", nativeQuery = true)
    public fun getRecords():List<RecordsEntity>

    @Query(
    "select" +
            "   id," +
            "   name," +
            "   registeredin," +
            "   total," +
            "   price," +
            "   item_id," +
            "   theamount," +
            "   type" +
            "   from records" +
            "   where records.type = '1';",
        nativeQuery = true
    )
    public fun getRecordsWhereTypeEntrance() : List<RecordsEntity>

    @Query(
        "select" +
                "   id," +
                "   name," +
                "   registeredin," +
                "   total," +
                "   price," +
                "   item_id," +
                "   theamount," +
                "   type" +
                "   from records" +
                "   where records.type = '2';",
        nativeQuery = true
    )
    public fun getRecordsWhereTypeExit() : List<RecordsEntity>
}