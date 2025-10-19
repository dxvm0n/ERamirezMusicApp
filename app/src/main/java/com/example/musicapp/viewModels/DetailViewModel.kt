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

sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Success(val album: Album) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

class DetailViewModel(
    private val repo: AlbumRepository = AlbumRepository()
) : ViewModel() {

    var uiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    fun loadAlbum(id: String) {
        viewModelScope.launch {
            uiState = try {
                DetailUiState.Success(repo.fetchAlbum(id))
            } catch (e: Exception) {
                DetailUiState.Error(e.message ?: "Network error")
            }
        }
    }
}
