package com.olxbr.android.challenge.listing.domain

import com.olxbr.android.challenge.listing.data.datasource.remote.RetrofitService
import com.olxbr.android.challenge.listing.model.Ad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdsRepository(private val retrofitService: RetrofitService) {
    suspend fun getAds(): Response<List<Ad>>{
        return withContext(Dispatchers.IO){
            try {
                Response.Success(
                    retrofitService.getAds().map {
                        Ad(it.thumbnail,
                        it.subject,
                        it.price,
                        it.time,
                        it.location)
                    }
                )
            } catch (exception: Exception){
                Response.Error(exception)
            }
        }
    }
}