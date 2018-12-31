package fontaine.nice.multiscreencontroller.dagger

import dagger.Component
import fontaine.nice.multiscreencontroller.views.ScreenFragment
import fontaine.nice.multiscreencontroller.views.ShaderFragment
import fontaine.nice.multiscreencontroller.views.TimeResetFragment
import javax.inject.Singleton

@Singleton @Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(timeResetFragment: TimeResetFragment)
    fun inject(screenFragment: ScreenFragment)
    fun inject(shaderFragment: ShaderFragment)
}
