package com.olxbr.android.challenge.listing.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.olxbr.android.challenge.ui.theme.DesafioAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Listing(
    state: ListingState.Success,
    onAction: (ListingAction) -> Unit = {}
) {
    Column {
        TextField(
            value = state.query ?: "",
            onValueChange = {
                onAction(ListingAction.Filter(it))
            },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            label = { Text(text = "Pesquisar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        LazyColumn {
            items(state.ads) { ad ->
                AdCard(ad)
            }
        }
    }
}

@Composable
fun ListingStateError(state: ListingState.Error){
    Column(verticalArrangement = Arrangement.Center) {
        Text(text = state.message,
        modifier = Modifier.align(CenterHorizontally))
        Icon(imageVector = Icons.Rounded.Warning , contentDescription = "warning error icon", tint = MaterialTheme.colorScheme.error,
        modifier = Modifier
            .size(40.dp)
            .align(CenterHorizontally))
    }
}

@Composable
fun ListingStateLoading(state: ListingState.Loading){
    Column(verticalArrangement = Arrangement.Center) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewListingErrorScreen(){
    DesafioAndroidTheme() {
        val state: ListingState.Error = ListingState.Error("An error has ocurred")
        ListingStateError(state)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewListingLoadingScreen(){
    DesafioAndroidTheme() {
        val state: ListingState.Loading = ListingState.Loading()
        ListingStateLoading(state)
    }
}
