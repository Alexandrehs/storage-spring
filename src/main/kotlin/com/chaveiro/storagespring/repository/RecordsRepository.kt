package com.chaveiro.storagespring.repository

import com.chaveiro.storagespring.entities.RecordsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
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
            "   from records" +
            "   order by registeredin asc;", nativeQuery = true)
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

    @Query("select " +
            "   sum(cast(total as integer)) as total, " +
            "   sum(cast(theamount as integer)) as quantidade, " +
            "   name," +
            "   case when type = '1' then 'entrada' when type = '2' then 'saida' end" +
            "   from records " +
            "   where type = :type " +
            "   group by name, type;", nativeQuery = true)
    public fun getRecordsGroupBytype(@Param("type") type: String) : List<IRecordsOrderByType>
}

public interface IRecordsOrderByType {
    val total: String
    val quantidade: String
    val name: String
    val case: String
}