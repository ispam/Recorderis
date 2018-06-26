package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "documents")
class Document(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "document_id")val id: Int,
               @ColumnInfo(name = "doc_name") val name: String,
               @ColumnInfo(name = "doc_symbol") val symbol: String)
