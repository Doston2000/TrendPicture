package uz.lazydevelopers1.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import uz.lazydevelopers1.domain.models.UnsplashPhoto
import uz.lazydevelopers1.domain.repository.UnsplashPhotoRepository
import javax.inject.Inject

class UnsplashPhotoInteractor @Inject constructor(private val unsplashPhotoRepository: UnsplashPhotoRepository) {

    fun getUnsplashPhotos(
        clientId: String,
        page: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<Result<List<UnsplashPhoto>>> {
        return unsplashPhotoRepository.getUnsplashPhotos(clientId, page, pageSize, orderBy).map {
            Result.success(it)
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

}