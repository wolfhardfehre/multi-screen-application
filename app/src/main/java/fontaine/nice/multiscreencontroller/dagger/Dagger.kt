package fontaine.nice.multiscreencontroller.dagger

import fontaine.nice.multiscreencontroller.Controller

private const val MESSAGE = "Dagger not initialized."

object Dagger {
    private var component: AppComponent? = null

    fun getComponent(): AppComponent {
        if (component == null) throw IllegalStateException(MESSAGE)
        return component as AppComponent
    }

    fun init(controller: Controller) {
        if (component != null) return
        this.component = DaggerAppComponent.builder()
            .appModule(AppModule(controller))
            .build()
    }
}
