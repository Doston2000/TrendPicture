package uz.lazydevelopers1.data.network

import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import uz.lazydevelopers1.domain.models.UnsplashPhoto

interface ApiService {

    @GET("photos")
    fun getUnsplashPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
        @Query("order_by") orderBy: String,
    ): Flow<List<UnsplashPhoto>>

}