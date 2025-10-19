package com.example.musicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicapp.components.*
import com.example.musicapp.models.Song
import com.example.musicapp.viewModels.HomeUiState
import com.example.musicapp.viewModels.HomeViewModel

private val ScreenGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFFF6F2FF),
        Color(0xFFEDE6FF),
        Color(0xFFE2DAFF)
    ),
    startY = Float.POSITIVE_INFINITY,
    endY = 0f
)

@Composable
fun HomeScreen(
    onAlbumClick: (String) -> Unit = {},
    vm: HomeViewModel = viewModel()
) {
    when (val s = vm.uiState) {
        is HomeUiState.Loading -> Box(
            modifier = Modifier.fillMaxSize().background(ScreenGradient),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }

        is HomeUiState.Error -> Box(
            modifier = Modifier.fillMaxSize().background(ScreenGradient),
            contentAlignment = Alignment.Center
        ) { Text("Error: ${s.message}", color = MaterialTheme.colorScheme.error) }

        is HomeUiState.Success -> {
            val albums = s.albums
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ScreenGradient)
            ) {
                LazyColumn(contentPadding = PaddingValues(bottom = 120.dp)) {
                    item { HomeHeaderCard(name = "Emiliano R") }

                    item { SectionTitleRow("Albums") }

                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(albums) { a ->
                                AlbumCardLarge(
                                    imageUrl = a.image,
                                    title = a.title,
                                    artist = a.artist,
                                    onClick = { onAlbumClick(a.id) }
                                )
                            }
                        }
                    }

                    item {
                        SectionTitleRow(
                            title = "Recently Played",
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }

                    items(albums) { a ->
                        SongItemCard(
                            song = Song(
                                title = a.title,
                                artist = a.artist,
                                image = a.image,
                                albumId = a.id
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            onClick = { onAlbumClick(a.id) }
                        )
                    }
                }

                if (albums.isNotEmpty()) {
                    MiniPlayerBar(
                        cover = albums.first().image,
                        title = albums.first().title,
                        artist = albums.first().artist,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }
}
