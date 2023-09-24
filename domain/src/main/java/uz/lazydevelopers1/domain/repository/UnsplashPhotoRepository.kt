package uz.lazydevelopers1.domain.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.lazydevelopers1.domain.models.UnsplashPhoto

interface UnsplashPhotoRepository {

    fun getUnsplashPhotos(
        clientId: String,
        page: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<List<UnsplashPhoto>>

}