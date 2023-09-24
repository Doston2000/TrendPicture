package uz.lazydevelopers1.trendpictures.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import uz.lazydevelopers1.data.network.ApiService
import uz.lazydevelopers1.data.repository.UnsplashPhotoRepositoryImplementation
import uz.lazydevelopers1.domain.repository.UnsplashPhotoRepository
import javax.inject.Singleton

@Module(includes = [DataModule.BindModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Module
    abstract class BindModule() {
        @Binds
        abstract fun bindApiRepository(unsplashPhotoRepositoryImplementation: UnsplashPhotoRepositoryImplementation): UnsplashPhotoRepository
    }

}