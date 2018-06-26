package tech.destinum.recorderis.DI

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import tech.destinum.recorderis.App
import tech.destinum.recorderis.Data.DAO.DateDAO
import tech.destinum.recorderis.Data.DAO.UserDAO
import tech.destinum.recorderis.Data.RecorderisDB
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    companion object {
        private var INSTANCE: RecorderisDB? = null

        private fun getInstance(context: Context): RecorderisDB?{
            if (INSTANCE == null) {
                synchronized(RecorderisDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RecorderisDB::class.java,
                            "recorderis.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

    @Provides @Singleton
    fun provideApp() = app

    @Provides @Singleton
    fun getDB(context: Context): RecorderisDB? = getInstance(context)

    @Provides @Singleton
    fun provideUserDAO(db: RecorderisDB): UserDAO = db.getUserDao()

    @Provides @Singleton
    fun provideDateDAO(db: RecorderisDB): DateDAO = db.getDateDao();



}