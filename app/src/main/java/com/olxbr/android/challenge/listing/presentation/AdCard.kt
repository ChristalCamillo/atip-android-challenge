package com.olxbr.android.challenge.listing.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
            .fillMaxWidth()
            .height(180.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom) {
                AsyncImage(
                    model = ad.thumbnail,
                    contentDescription = "Translated description of what the image contains",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(140.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()) {
                            Text(text = ad.subject, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))

                            ad.price?.let { Text(text = it, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(bottom = 16.dp)) }

                        Row {
                        ad.time?.let { Text(text = it, color = Color.Gray, fontSize = 10.sp)}
                        ad.location?.let { Text(text = it, color = Color.Gray, fontSize = 10.sp)}
                    }
                }
            }
        }
    }
}
