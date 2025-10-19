package com.example.musicapp.models

import java.io.Serializable

data class Song(
    val title: String,
    val artist: String,
    val image: String,
    val albumId: String? = null
)
