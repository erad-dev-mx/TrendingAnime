package dev.erad.tinyapps.ui.screen.anime_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.erad.tinyapps.domain.model.AnimeData
import dev.erad.tinyapps.domain.repository.KitsuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingAnimeListViewModel @Inject constructor(
    private val repository: KitsuRepository
) : ViewModel() {
    private var _animeList = MutableStateFlow<List<AnimeData>>(emptyList())
    val animeList = _animeList.asStateFlow()

    init {
        Log.d("TrendingAnimeListViewModel", "init block started")
        viewModelScope.launch {
            try {
                Log.d("TrendingAnimeListViewModel", "Fetching trending anime list")
                val trendingAnimeList = repository.getTrendingAnimeList()
                _animeList.emit(trendingAnimeList)
                Log.d("TrendingAnimeListViewModel", "Anime list fetched and emitted")
            } catch (e: Exception) {
                Log.d("TrendingAnimeListViewModel", "Error: $e")
            }
        }
    }
}