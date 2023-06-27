package com.olxbr.android.challenge

import com.olxbr.android.challenge.listing.data.datasource.local.localAds
import com.olxbr.android.challenge.listing.data.datasource.remote.RetrofitService
import com.olxbr.android.challenge.listing.domain.AdsRepository
import com.olxbr.android.challenge.listing.domain.Response
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AdsRepositoryTest {
    private val retrofitService: RetrofitService = mockk()
    private val repository = AdsRepository(retrofitService)

    @Test
    fun whenQueryIsEmptyReturnListAds() = runTest {
        coEvery { retrofitService.getAds() } returns localAds
        val query = repository.getAds(query = "")
        assert(query is Response.Success)
        val resultSuccess = query as Response.Success
        assert(resultSuccess.data.size == localAds.size)
    }

    @Test
    fun searchBarShouldFilterBasedOnEntireSubjectAndCaseInsensitive() = runTest {
        coEvery { retrofitService.getAds() } returns localAds
        val query = repository.getAds(query = "caiXa")
        assert(query is Response.Success)
        val resultSuccess = query as Response.Success
        assert(resultSuccess.data.size != localAds.size)
        assert(resultSuccess.data.size == 2)
    }
}