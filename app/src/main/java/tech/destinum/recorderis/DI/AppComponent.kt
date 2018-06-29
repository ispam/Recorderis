package tech.destinum.recorderis.DI

import dagger.Component
import org.jetbrains.annotations.Nullable
import tech.destinum.recorderis.App
import tech.destinum.recorderis.activities.Form
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun inject(app: App)

    fun inject(form: Form)
}