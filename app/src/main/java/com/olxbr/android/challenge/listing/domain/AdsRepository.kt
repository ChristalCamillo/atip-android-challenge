package com.olxbr.android.challenge.listing.domain

import com.olxbr.android.challenge.listing.data.datasource.remote.RetrofitService
import com.olxbr.android.challenge.listing.model.Ad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdsRepository(private val retrofitService: RetrofitService) {
    suspend fun getAds(query: String): Response<List<Ad>> {
        return withContext(Dispatchers.IO) {
            try {
                val ads = retrofitService.getAds().map {
                    Ad(
                        it.thumbnail,
                        it.subject,
                        it.price,
                        it.time,
                        it.location
                    )
                }
                var result = ads
                if (query.isNotEmpty()) {
                    result = ads.filter { ad ->
                        Regex(
                            query,
                            RegexOption.IGNORE_CASE
                        ).containsMatchIn(ad.subject)
                    }
                }
                Response.Success(
                    result

                )
            } catch (exception: Exception) {
                Response.Error(exception)
            }
        }
    }
}