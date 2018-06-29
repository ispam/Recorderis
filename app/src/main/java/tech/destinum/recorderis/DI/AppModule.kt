package tech.destinum.recorderis.DI

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import tech.destinum.recorderis.App
import tech.destinum.recorderis.Data.RecorderisDB
import tech.destinum.recorderis.Data.ViewModels.DateViewModel
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides @Singleton
    fun provideApp() = app

    @Provides @Singleton
    fun getDB(context: App): RecorderisDB = Room.databaseBuilder(context.applicationContext,
            RecorderisDB::class.java,
            "recorderis.db")
            .build()

    @Provides @Singleton
    fun provideDateVM(db: RecorderisDB): DateViewModel {
        return DateViewModel(db)
    }

}