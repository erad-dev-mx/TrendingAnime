package dev.erad.tinyapps.data.repository

import android.util.Log
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dev.erad.tinyapps.data.network.KitsuApi
import dev.erad.tinyapps.domain.model.AnimeData
import dev.erad.tinyapps.domain.repository.KitsuRepository
import javax.inject.Inject

class KitsuRepositoryImpl @Inject constructor(
    private val api: KitsuApi
) : KitsuRepository {

    override suspend fun getTrendingAnimeList(): List<AnimeData> {
        var animeData: List<AnimeData> = emptyList()

        api.getTrendingAnimeList()
            .onSuccess {
                Log.d("getTrendingAnime", "success")
                animeData = data.toModel()
            }
            .onError {
                Log.d("getTrendingAnime", "error - ${this.message()}")
            }
            .onException {
                Log.d("getTrendingAnime", "exception - ${this.message}")
            }

        return animeData
    }

    override suspend fun getAnimeById(id: Int): AnimeData? {
        var anime: AnimeData? = null
        api.getAnimeById(id)
            .onSuccess {
                Log.d("getAnime", "success")

                anime = data?.toModel()
            }
            .onError {
                Log.d("getAnime", "error - ${this.message()}")
            }
            .onException {
                Log.d("getAnime", "exception - ${this.message}")
            }

        return anime
    }
}