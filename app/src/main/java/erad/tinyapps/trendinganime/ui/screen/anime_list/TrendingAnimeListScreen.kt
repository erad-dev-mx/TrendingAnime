package erad.tinyapps.trendinganime.ui.screen.anime_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import erad.tinyapps.trendinganime.R
import erad.tinyapps.trendinganime.ui.screen.anime_list.composable.AnimeCard

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TrendingAnimeListScreen(
    modifier: Modifier = Modifier,
    onAnimeClick: (String?, Int?) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: TrendingAnimeListViewModel = hiltViewModel()
) {
    val animeList by viewModel.animeList.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        AnimatedContent(targetState = animeList.isEmpty(), label = "") { isEmpty ->
            if (isEmpty) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    )
                ) {
                    item {
                        Text(
                            text = stringResource(R.string.title_trending),
                            style = MaterialTheme.typography.displayLarge
                        )
                    }

                    items(animeList) { anime ->
                        AnimeCard(anime = anime, onClick = {
                            onAnimeClick(anime.attributes.posterImage.original, anime.id.toInt())
                        }, animatedVisibilityScope = animatedVisibilityScope)
                    }
                }
            }
        }
    }
}