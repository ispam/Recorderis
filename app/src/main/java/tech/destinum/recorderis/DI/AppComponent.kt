package tech.destinum.recorderis.DI

import dagger.Component
import tech.destinum.recorderis.App
import tech.destinum.recorderis.activities.Form
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)
    fun inject(activity: Form)
}