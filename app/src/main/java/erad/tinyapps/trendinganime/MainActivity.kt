@file:OptIn(ExperimentalSharedTransitionApi::class)

package erad.tinyapps.trendinganime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import erad.tinyapps.trendinganime.ui.screen.anime.AnimeScreen
import erad.tinyapps.trendinganime.ui.screen.anime_list.TrendingAnimeListScreen
import erad.tinyapps.trendinganime.ui.theme.TrendingAnimeTheme
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            TrendingAnimeTheme {
                val navController = rememberNavController()

                SharedTransitionLayout {
                    NavHost(
                        navController = navController,
                        startDestination = TrendingAnimeRoute
                    ) {
                        composable<TrendingAnimeRoute> {
                            TrendingAnimeListScreen(
                                onAnimeClick = { cover, id ->
                                    navController.navigate(
                                        AnimeRoute(
                                            id = id.toString(),
                                            coverUrl = cover.toString()
                                        )
                                    )
                                },
                                animatedVisibilityScope = this
                            )
                        }

                        composable<AnimeRoute> {
                            val args = it.toRoute<AnimeRoute>()

                            AnimeScreen(
                                id = (args.id ?: 0).toString(),
                                coverImage = args.coverUrl.toString(),
                                animatedVisibilityScope = this
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
data object TrendingAnimeRoute

@Serializable
data class AnimeRoute(val coverUrl: String?, val id: String?)