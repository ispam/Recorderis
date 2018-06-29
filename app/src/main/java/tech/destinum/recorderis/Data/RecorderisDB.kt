package tech.destinum.recorderis.Data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import tech.destinum.recorderis.Data.DAO.DateDAO
import tech.destinum.recorderis.Data.Entities.Category
import tech.destinum.recorderis.Data.Entities.Date
import tech.destinum.recorderis.Data.Entities.Document

@Database(entities = arrayOf(Document::class, Category::class, Date::class), version = 1, exportSchema = false)
abstract class RecorderisDB : RoomDatabase(){

    abstract fun getDateDao(): DateDAO
}