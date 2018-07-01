package tech.destinum.recorderis.Data.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "documents")
data class Document(@ColumnInfo(name = "doc_name") var name: String,
               @ColumnInfo(name = "doc_symbol") var symbol: String){
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "document_id")
    var id: Int? = null
}
