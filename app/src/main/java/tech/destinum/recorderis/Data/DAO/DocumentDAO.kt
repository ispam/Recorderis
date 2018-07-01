package tech.destinum.recorderis.Data.DAO

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import tech.destinum.recorderis.Data.Entities.Document

@Dao
interface DocumentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(document: Document)


}