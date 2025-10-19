package com.example.musicapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.models.Album
import com.example.musicapp.services.AlbumRepository
import kotlinx.coroutines.launch

//  Usare este viewModel como base para crear la screen: Recuerda que el viewModel define la estructura logica.
sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val albums: List<Album>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

class HomeViewModel(
    private val repository: AlbumRepository = AlbumRepository()
) : ViewModel() {

    var uiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init { loadAlbums() }

    fun loadAlbums() {
        viewModelScope.launch {
            uiState = try {
                HomeUiState.Success(repository.fetchAlbums())
            } catch (e: Exception) {
                HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
