package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
class User(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val id: Long,
           @ColumnInfo(name = "user_name") val name: String,
           @ColumnInfo(name = "user_email") val email: String)