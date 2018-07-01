package tech.destinum.recorderis.Data.DAO

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Observable
import tech.destinum.recorderis.Data.Entities.Date

@Dao
interface DateDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newDate(date: Date)

    @Query("select * from dates")
    fun getAll() : Flowable<List<Date>>

    @Query("update dates set date_name = :name, date = :date, date_symbol = :symbol where date_id = :id")
    fun update(name: String, date: String, symbol: String, id: Long)

    @Query("delete from dates where date_id = :id")
    fun delete(id: Long)
}