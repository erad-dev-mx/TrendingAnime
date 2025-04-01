package erad.tinyapps.trendinganime.domain.repository

import erad.tinyapps.trendinganime.domain.model.AnimeData

interface KitsuRepository {
    suspend fun getTrendingAnimeList(): List<AnimeData>

    suspend fun getAnimeById(id: Int): AnimeData?
}