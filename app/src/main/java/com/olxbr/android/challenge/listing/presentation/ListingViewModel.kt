package com.olxbr.android.challenge.listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.olxbr.android.challenge.listing.data.datasource.remote.RetrofitService
import com.olxbr.android.challenge.listing.domain.AdsRepository
import com.olxbr.android.challenge.listing.model.Ad
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class ListingState {

    object Uninitialized : ListingState()

    data class Loading(val query: String? = null) : ListingState()

    data class Success(val ads: List<Ad>, val query: String? = null) : ListingState()

    data class Error(val message: String) : ListingState()
}

sealed class ListingAction {

    object Initialize : ListingAction()

    data class Filter(val query: String) : ListingAction()
}

class ListingViewModel(
    private val repository: AdsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _state = MutableStateFlow<ListingState>(ListingState.Uninitialized)
    val state: StateFlow<ListingState> = _state

    fun onAction(action: ListingAction) {
        when (action) {
            is ListingAction.Initialize -> initialize()
            is ListingAction.Filter -> filter(action.query)
        }
    }

    private fun initSearchBarAndAdsList(query: String=""){
        viewModelScope.launch(dispatcher){
            when(val ads: Response<List<Ad>> = repository.getAds()){

            }
        }
    }

    private fun initialize() {
initSearchBarAndAdsList()
    }

    private fun filter(query: String) {
        viewModelScope.launch(dispatcher) {
            val result =
                repository.getAds().filter { ad -> ad.subject.startsWith(query) }

            _state.update { ListingState.Success(result, query) }
        }
    }
}



class ListingViewModelFactory(private val retrofitService: RetrofitService) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListingViewModel::class.java)) {
            val repository = AdsRepository(retrofitService)
            return ListingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

