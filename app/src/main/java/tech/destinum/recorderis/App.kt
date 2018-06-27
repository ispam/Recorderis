package tech.destinum.recorderis

import android.app.Application
import tech.destinum.recorderis.DI.AppComponent
import tech.destinum.recorderis.DI.AppModule
import tech.destinum.recorderis.DI.DaggerAppComponent

open class App: Application() {

    companion object {
        @JvmStatic lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        graph.inject(this)

    }
}