package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dates")
data class Date(@ColumnInfo(name = "date_name") var name: String,
                @ColumnInfo(name = "date_symbol") var symbol: String,
                @ColumnInfo(name = "date") var date: String) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "date_id")
    var id: Long? = null
}

