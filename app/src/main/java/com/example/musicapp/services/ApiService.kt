package com.example.musicapp.services

import com.example.musicapp.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") id: String): Album
}
