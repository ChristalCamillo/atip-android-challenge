package com.olxbr.android.challenge.listing.data.datasource.remote

import com.olxbr.android.challenge.listing.data.datasource.local.localAds
import com.olxbr.android.challenge.listing.model.Ad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitService {

    suspend fun getAds(): List<Ad> {
        return withContext(Dispatchers.IO) {
            localAds
        }
    }
}
