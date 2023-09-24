package uz.lazydevelopers1.trendpictures

import android.app.Application
import uz.lazydevelopers1.trendpictures.di.AppComponent
import uz.lazydevelopers1.trendpictures.di.DaggerAppComponent
import javax.inject.Inject

class App @Inject constructor() : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(this)

        appComponent.inject(this)
    }

}