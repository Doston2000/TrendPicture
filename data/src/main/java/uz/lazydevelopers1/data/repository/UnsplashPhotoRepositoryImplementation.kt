package uz.lazydevelopers1.data.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.lazydevelopers1.data.network.ApiService
import uz.lazydevelopers1.domain.models.UnsplashPhoto
import uz.lazydevelopers1.domain.repository.UnsplashPhotoRepository
import javax.inject.Inject

class UnsplashPhotoRepositoryImplementation @Inject constructor(private val apiService: ApiService) :
    UnsplashPhotoRepository {
    override fun getUnsplashPhotos(
        clientId: String,
        page: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<List<UnsplashPhoto>> {
        return apiService.getUnsplashPhotos(clientId, page, pageSize, orderBy)
    }
}