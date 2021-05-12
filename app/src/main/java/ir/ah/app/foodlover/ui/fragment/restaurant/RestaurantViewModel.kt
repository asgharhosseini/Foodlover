package ir.ah.app.foodlover.ui.fragment.restaurant

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.data.model.order.*
import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.restaurant.*
import ir.ah.app.foodlover.other.*
import kotlinx.coroutines.*
import java.io.*
import javax.inject.*

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val repository: RestaurantRepositoryImpl) :
        BaseViewModel() {

    private val _restaurant = MutableLiveData<Resource<Restaurant>>()
    val restaurant: LiveData<Resource<Restaurant>> = _restaurant

    val totalSum = repository.observeTotalSum()
    val totalCount = repository.observeTotalCount()
    val allOrderItems = repository.observeAllOrderItems()


    fun getRestaurant(restaurantId: Int = 1) = viewModelScope.launch {
        _restaurant.postValue(Resource.Loading())
        try {
            val response = repository.getRestaurant(restaurantId)
            if (response != null) {
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

    fun deleteOrderItem(order: Order) = viewModelScope.launch {
        repository.deleteShoppingItem(order)
    }

    fun insertOrderItemIntoDb(order: Order) = viewModelScope.launch {
        repository.insertShoppingItem(order)
    }

}