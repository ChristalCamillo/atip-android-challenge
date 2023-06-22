package com.olxbr.android.challenge.listing.domain

import com.olxbr.android.challenge.listing.data.datasource.remote.RetrofitService
import com.olxbr.android.challenge.listing.model.Ad

class AdsRepository(private val retrofitService: RetrofitService) {
    suspend fun getAds(): List<Ad>{
        return retrofitService.getAds()
    }
}