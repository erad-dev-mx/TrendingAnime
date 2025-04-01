package erad.tinyapps.trendinganime.ui.screen.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import erad.tinyapps.trendinganime.domain.model.AnimeData
import erad.tinyapps.trendinganime.domain.repository.KitsuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    val repository: KitsuRepository
) : ViewModel() {
    private var _anime = MutableStateFlow<AnimeData?>(null)
    val anime = _anime

    fun getAnimeById(id: Int) {
        viewModelScope.launch {
            _anime.update {
                repository.getAnimeById(id)
            }
        }
    }
}