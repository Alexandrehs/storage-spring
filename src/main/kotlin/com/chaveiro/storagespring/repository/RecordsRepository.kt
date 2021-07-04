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
            "   sum(cast(theamount as integer)) as the_amount, " +
            "   name," +
            "   case when type = '1' then 'entrada' when type = '2' then 'saida' end type_action" +
            "   from records " +
            "   where type = :type " +
            "   and registeredin between cast(:date_init as date) and cast(:date_final as date)" +
            "   group by name, type;", nativeQuery = true)
    public fun getRecordsGroupBytype(
        @Param("date_init")date_init: String,
        @Param("date_final")date_final: String,
        @Param("type") type: String
    ) : List<IRecordsOrderByType>

    @Query("select" +
            "   sum(cast(total as integer)) as total," +
            "   sum(cast(theamount as integer)) as the_amount," +
            "   case when type = '1' then 'entrada' when type = '2' then 'saida' end  type_action" +
            "   from records" +
            "   where type = :type" +
            "   and registeredin between cast(:date_init as date) and cast(:date_final as date)" +
            "   group by type;", nativeQuery = true)
    public fun getRecordsTotalByBetweenDate(
        @Param("date_init")date_init: String,
        @Param("date_final")date_final: String,
        @Param("type")type: String
    ) : List<IRecordsTotalBetweenDate>

}

public interface IRecordsOrderByType {
    val total: String
    val thea_amount: String
    val name: String
    val type_action: String
}

public interface IRecordsTotalBetweenDate {
    val total: String
    val the_amount: String
    val type_action: String
}