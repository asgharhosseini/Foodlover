package ir.ah.app.foodlover.ui.fragment.allrestaurant

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.home.*
import ir.ah.app.foodlover.other.*
import kotlinx.coroutines.*
import java.io.*
import javax.inject.*

@HiltViewModel
class AllRestaurantViewModel @Inject constructor(private val repository: HomeRepositoryImpl) : BaseViewModel() {
    private val _restaurant = MutableLiveData<Resource<List<Restaurant>>>()
    val restaurant: LiveData<Resource<List<Restaurant>>> = _restaurant

    init {
        getAllRestaurant()
    }

    fun getAllRestaurant() = viewModelScope.launch {
        _restaurant.postValue(Resource.Loading())
        try {
            val response = repository.getAllRestaurant()
            if (!response.isNullOrEmpty()) {
                _restaurant.postValue(Resource.Success(response))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _restaurant.postValue(
                        Resource.Error(
                                "Network Failure",
                                null,
                                404
                        )
                )
                else -> _restaurant.postValue(Resource.Error("Conversion Error", null, 404))
            }

        }
    }
}