package ir.ah.app.foodlover.ui.fragment.search

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.search.*
import ir.ah.app.foodlover.other.*
import kotlinx.coroutines.*
import java.io.*
import javax.inject.*

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepositoryImpl) : BaseViewModel() {


    private val _SearchRestaurant = MutableLiveData<Resource<List<Restaurant>>>()
    val SearchRestaurant: LiveData<Resource<List<Restaurant>>> = _SearchRestaurant


    fun getSearch(searchQuery: String) = viewModelScope.launch {
        _SearchRestaurant.postValue(Resource.Loading())
        try {
            val response = repository.getSearch(searchQuery)
            if (!response.isNullOrEmpty()) {
                _SearchRestaurant.postValue(Resource.Success(response))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _SearchRestaurant.postValue(
                        Resource.Error(
                                "Network Failure",
                                null,
                                404
                        )
                )
                else -> _SearchRestaurant.postValue(Resource.Error("Conversion Error", null, 404))
            }

        }


    }


}