package fontaine.nice.multiscreencontroller

import android.app.Application
import fontaine.nice.multiscreencontroller.dagger.Dagger
import fontaine.nice.multiscreencontroller.models.Screen
import fontaine.nice.multiscreencontroller.models.Shader

private const val OFF = 0

class Controller : Application() {
    val screens = (1..11).map { number -> Screen(number, OFF) }.toList()
    val shader = (1..17).map { number -> Shader(number) }.toList()

    override fun onCreate() {
        super.onCreate()
        Dagger.init(this)
    }
}
