package com.example.musicapp.models

data class Song(
    val title: String,
    val artist: String,
    val image: String,
    val albumId: Int? = null
)
