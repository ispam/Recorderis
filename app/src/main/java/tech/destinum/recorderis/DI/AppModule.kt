package tech.destinum.recorderis.DI

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import tech.destinum.recorderis.App
import tech.destinum.recorderis.Data.RecorderisDB
import tech.destinum.recorderis.Data.ViewModels.DateViewModel
import tech.destinum.recorderis.Data.ViewModels.DocumentViewModel
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides @Singleton
    fun provideApp() = app

    @Provides @Singleton
    fun getDB(app: App): RecorderisDB = Room.databaseBuilder(app,
            RecorderisDB::class.java,
            "recorderis.db")
            .build()

    @Provides @Singleton
    fun provideDateVM(db: RecorderisDB): DateViewModel = DateViewModel(db)


    @Provides @Singleton
    fun provideDocumentVM(db: RecorderisDB):DocumentViewModel = DocumentViewModel(db)

}