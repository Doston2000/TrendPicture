package uz.lazydevelopers1.trendpictures.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import uz.lazydevelopers1.domain.interactor.UnsplashPhotoInteractor
import javax.inject.Inject

class UnsplashPhotoViewModel @Inject constructor(private val unsplashPhotoInteractor: UnsplashPhotoInteractor) :
    ViewModel() {

    fun getUnsplashPhotos(
        clientId: String,
        page: Int,
        pageSize: Int,
        orderBy: String
    ): StateFlow<UnsplashPhotoResource> {
        val stateFlow = MutableStateFlow<UnsplashPhotoResource>(UnsplashPhotoResource.Loading)
        viewModelScope.launch {
            unsplashPhotoInteractor.getUnsplashPhotos(clientId, page, pageSize, orderBy)
                .catch { stateFlow.emit(UnsplashPhotoResource.Error(it.message ?: "")) }
                .collect {
                    if (it.isSuccess) {
                        stateFlow.emit(UnsplashPhotoResource.Success(it.getOrNull()))
                    } else if (it.isFailure) {
                        stateFlow.emit(UnsplashPhotoResource.Error(it.exceptionOrNull().toString()))
                    }
                }
        }
        return stateFlow
    }

}