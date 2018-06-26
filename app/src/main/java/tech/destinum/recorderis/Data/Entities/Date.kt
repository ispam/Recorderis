package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dates")
class Date(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "date_id") val id: Long,
           @ColumnInfo(name = "") val name: String,
           @ColumnInfo(name = "symbol") val symbol: String,
           @ColumnInfo(name = "date") val date: String,
           @ColumnInfo(name = "user_id") val user_id: Long)

