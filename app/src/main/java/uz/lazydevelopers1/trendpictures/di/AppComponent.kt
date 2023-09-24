package uz.lazydevelopers1.trendpictures.di

import dagger.BindsInstance
import dagger.Component
import uz.lazydevelopers1.trendpictures.App
import uz.lazydevelopers1.trendpictures.di.module.ApplicationModule
import uz.lazydevelopers1.trendpictures.di.module.DataModule
import uz.lazydevelopers1.trendpictures.di.module.ViewModelModule
import uz.lazydevelopers1.trendpictures.presentation.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: App):AppComponent
    }

    fun inject(app: App)
    fun inject(mainActivity: MainActivity)

}