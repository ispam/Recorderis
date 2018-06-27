package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dates")
data class Date(@ColumnInfo(name = "date_name") val name: String,
                @ColumnInfo(name = "date_symbol") val symbol: String,
                @ColumnInfo(name = "date") val date: String,
                @ColumnInfo(name = "date_user_id") val user_id: Long) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "date_id")
    val id: Long? = null
}

