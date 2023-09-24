package uz.lazydevelopers1.trendpictures.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.lazydevelopers1.trendpictures.vm.UnsplashPhotoViewModel
import uz.lazydevelopers1.trendpictures.vm.ViewModelFactory
import uz.lazydevelopers1.trendpictures.vm.ViewModelKey

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UnsplashPhotoViewModel::class)
    fun bindUnsplashPhotoViewModel(unsplashPhotoViewModel: UnsplashPhotoViewModel): ViewModel

}