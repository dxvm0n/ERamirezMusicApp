package com.example.musicapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SectionTitleRow(
    title: String,
    showMoreText: String = "See more",
    onSeeMore: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            showMoreText,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF8A56F8),
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.clickable(enabled = onSeeMore != null) { onSeeMore?.invoke() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionTitleRowPreview() {
    MaterialTheme {
        SectionTitleRow("Albums")
    }
}
