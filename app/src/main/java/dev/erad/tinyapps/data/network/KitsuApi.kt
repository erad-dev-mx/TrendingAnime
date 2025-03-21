package dev.erad.tinyapps.data.network

import com.skydoves.sandwich.ApiResponse
import dev.erad.tinyapps.data.network.dto.AnimeResponseDto
import dev.erad.tinyapps.data.network.dto.TrendingAnimeListResponseDto
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