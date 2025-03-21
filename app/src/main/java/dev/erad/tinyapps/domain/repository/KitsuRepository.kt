package dev.erad.tinyapps.domain.repository

import dev.erad.tinyapps.domain.model.AnimeData

interface KitsuRepository {
    suspend fun getTrendingAnimeList(): List<AnimeData>

    suspend fun getAnimeById(id: Int): AnimeData?
}