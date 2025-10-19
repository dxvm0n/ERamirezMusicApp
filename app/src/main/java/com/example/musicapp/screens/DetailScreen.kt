package com.example.musicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.musicapp.components.*
import com.example.musicapp.models.Song
import com.example.musicapp.viewModels.DetailUiState
import com.example.musicapp.viewModels.DetailViewModel

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
fun DetailScreen(
    navController: NavController,
    albumId: String,
    vm: DetailViewModel = viewModel()
) {
    LaunchedEffect(albumId) { vm.loadAlbum(albumId) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenGradient)
    ) {
        when (val state = vm.uiState) {
            is DetailUiState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

            is DetailUiState.Error -> Text(
                text = "Error: ${state.message}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )

            is DetailUiState.Success -> {
                val album = state.album
                val songs = remember(album.id) {
                    (1..10).map {
                        Song(
                            title = "${album.title} • Track $it",
                            artist = album.artist,
                            image = album.image,
                            albumId = album.id
                        )
                    }
                }

                LazyColumn(contentPadding = PaddingValues(bottom = 120.dp)) {
                    item {
                        DetailHeroHeader(
                            navController = navController,
                            imageUrl = album.image,
                            title = album.title,
                            artist = album.artist,
                            onPlay = { },
                            onShuffle = { }
                        )
                    }
                    item { AboutAlbumCard(description = album.description) }
                    item { ArtistChip(artist = album.artist) }
                    items(songs) { song ->
                        SongItemCard(
                            song = song,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }

                MiniPlayerBar(
                    cover = album.image,
                    title = album.title,
                    artist = album.artist,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
