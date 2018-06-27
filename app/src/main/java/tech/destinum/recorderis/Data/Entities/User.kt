package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
data class User @JvmOverloads constructor(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val id: Long = 1,
           @ColumnInfo(name = "user_name") val name: String,
           @ColumnInfo(name = "user_email") val email: String)
