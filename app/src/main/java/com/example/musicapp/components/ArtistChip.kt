package com.example.musicapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ArtistChip(artist: String, modifier: Modifier = Modifier) {
    AssistChip(
        onClick = {},
        label = { Text("Artist: $artist") },
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        shape = CircleShape
    )
}

