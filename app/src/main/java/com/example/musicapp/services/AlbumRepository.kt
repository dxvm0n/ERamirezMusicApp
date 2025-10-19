package com.example.musicapp.services

import com.example.musicapp.models.Album

class AlbumRepository(private val api: ApiService = NetworkModule.api) {

    suspend fun fetchAlbums(): List<Album> = api.getAlbums()

    suspend fun fetchAlbum(id: String): Album = api.getAlbum(id)
}
