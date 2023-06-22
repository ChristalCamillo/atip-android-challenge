package com.olxbr.android.challenge.listing.data.datasource.remote

import com.olxbr.android.challenge.listing.constants.Constants
import com.olxbr.android.challenge.listing.model.Ad
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.ADS_RESPONSE)
    suspend fun getAds(): List<Ad>
}

val retrofit: Retrofit? = Retrofit.Builder()
    .baseUrl(Constants.ADS_BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create()).build()

val adsService: RetrofitService = retrofit!!.create(RetrofitService::class.java)
