package uz.lazydevelopers1.trendpictures.vm

import uz.lazydevelopers1.domain.models.UnsplashPhoto

sealed class UnsplashPhotoResource {

    object Loading : UnsplashPhotoResource()
    data class Success(val list: List<UnsplashPhoto>?) : UnsplashPhotoResource()
    data class Error(val message: String?) : UnsplashPhotoResource()

}