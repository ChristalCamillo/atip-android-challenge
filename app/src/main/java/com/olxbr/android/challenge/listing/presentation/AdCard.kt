package com.olxbr.android.challenge.listing.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.olxbr.android.challenge.listing.model.Ad

@Composable
fun AdCard(ad: Ad) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row() {
                AsyncImage(
                    model = ad.thumbnail,
                    contentDescription = "Translated description of what the image contains"
                )
                Column() {
                    Row() {
                        Text(text = ad.subject, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Row() {
                        ad.price?.let { Text(text = it, fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Row() {
                        ad.time?.let { Text(text = it, color = Color.Gray, fontSize = 10.sp)}
                        ad.location?.let { Text(text = it, color = Color.Gray, fontSize = 10.sp)}
                    }
                }
            }
        }
    }
}
