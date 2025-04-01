package erad.tinyapps.trendinganime.data.network

import com.skydoves.sandwich.ApiResponse
import erad.tinyapps.trendinganime.data.network.dto.AnimeResponseDto
import erad.tinyapps.trendinganime.data.network.dto.TrendingAnimeListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface KitsuApi {
    @GET("trending/anime")
    suspend fun getTrendingAnimeList(): ApiResponse<TrendingAnimeListResponseDto>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): ApiResponse<AnimeResponseDto>

    companion object {
        const val BASE_URL = "https://kitsu.io/api/edge/"
    }
}