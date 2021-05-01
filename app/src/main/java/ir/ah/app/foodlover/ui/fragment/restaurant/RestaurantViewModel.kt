package ir.ah.app.foodlover.ui.fragment.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.app.foodlover.base.BaseViewModel
import ir.ah.app.foodlover.data.model.order.Order
import ir.ah.app.foodlover.data.model.restaurant.Restaurant
import ir.ah.app.foodlover.data.remot.repositoeies.restaurant.RestaurantRepositoryImpl
import ir.ah.app.foodlover.other.Resource
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val repository: RestaurantRepositoryImpl) :
        BaseViewModel() {

    private val _restaurant = MutableLiveData<Resource<Restaurant>>()
    val restaurant: LiveData<Resource<Restaurant>> = _restaurant

    val totalSum = repository.observeTotalSum()
    val totalCount = repository.observeTotalCount()
    val allOrderItems = repository.observeAllOrderItems()

    init {
        getAllRestaurant()
    }

    fun getAllRestaurant() = viewModelScope.launch {
        _restaurant.postValue(Resource.Loading())
        try {
            val response = repository.getRestaurant()
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