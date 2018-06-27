package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(@PrimaryKey(autoGenerate = true) val id: Long,
               @ColumnInfo(name = "name") val name: String,
               @ColumnInfo(name = "description") val description: String,
               @ColumnInfo(name = "image") @Ignore val image: Int)
