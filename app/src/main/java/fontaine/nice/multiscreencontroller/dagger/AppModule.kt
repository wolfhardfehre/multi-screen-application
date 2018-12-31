package fontaine.nice.multiscreencontroller.dagger

import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.SharedPreferences
import android.content.res.Resources
import fontaine.nice.multiscreencontroller.Controller
import fontaine.nice.multiscreencontroller.data.PersistentData
import fontaine.nice.multiscreencontroller.data.Session

@Module class AppModule(private val controller: Controller) {

    @Provides @Singleton
    internal fun controller() = controller

    @Provides @Singleton
    internal fun resources() = controller.resources

    @Provides @Singleton
    internal fun preferences() = PreferenceManager.getDefaultSharedPreferences(controller)

    @Provides @Singleton
    internal fun persistentData(resources: Resources, preferences: SharedPreferences) =
        PersistentData(resources, preferences)

    @Provides @Singleton
    internal fun session(persistence: PersistentData) = Session(persistence)
}
