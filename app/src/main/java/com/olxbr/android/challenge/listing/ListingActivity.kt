package com.olxbr.android.challenge.listing.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.olxbr.android.challenge.listing.data.datasource.remote.adsService

class MainActivity : ComponentActivity() {

    private val viewModel: ListingViewModel by viewModels { ListingViewModelFactory(adsService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.state.collectAsState()

            ListingScreen(state = state.value, onAction = viewModel::onAction)
        }
    }
}
