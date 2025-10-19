package com.example.musicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun AlbumCardLarge(
    imageUrl: String,
    title: String,
    artist: String,
    modifier: Modifier = Modifier,
    onPlay: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(width = 260.dp, height = 200.dp)
            .clip(RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
    ) {
        AsyncImage(model = imageUrl, contentDescription = null, modifier = Modifier.fillMaxSize())
        Box(
            Modifier
                .matchParentSize()
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xAA2D0F4F))))
        )
        Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
            Text(title, color = Color.White, style = MaterialTheme.typography.titleLarge)
            Text(artist, color = Color.White.copy(alpha = 0.9f))
        }
        FilledIconButton(
            onClick = onPlay,
            shape = CircleShape,
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White),
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null)
        }
    }
}

