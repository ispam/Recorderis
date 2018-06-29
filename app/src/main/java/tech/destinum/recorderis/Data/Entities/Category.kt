package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(@PrimaryKey(autoGenerate = true) var id: Long = 0,
               @ColumnInfo(name = "name") var name: String,
               @ColumnInfo(name = "description") var description: String,
               @ColumnInfo(name = "image") @Ignore var image: Int) {
    constructor() : this(0, "", "", 0)
}
