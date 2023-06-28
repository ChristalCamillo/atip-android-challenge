@file:OptIn(ExperimentalMaterial3Api::class)

package com.olxbr.android.challenge.listing.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.olxbr.android.challenge.listing.data.datasource.local.localAds
import com.olxbr.android.challenge.ui.theme.DesafioAndroidTheme

@Composable
fun ListingScreen(
    state: ListingState,
    onAction: (ListingAction) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        onAction(ListingAction.Initialize)
    }

    DesafioAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (state) {
                is ListingState.Uninitialized -> {}
                is ListingState.Error -> ListingStateError(state)
                is ListingState.Loading -> ListingStateLoading(state)
                is ListingState.Success -> Listing(state, onAction)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ListingScreenPreview(){
    DesafioAndroidTheme() {
        val state: ListingState.Success = ListingState.Success(localAds)
        Listing(state = state)

    }
}

