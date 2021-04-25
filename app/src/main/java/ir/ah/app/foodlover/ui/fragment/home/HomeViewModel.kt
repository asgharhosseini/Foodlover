package ir.ah.app.foodlover.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ah.app.foodlover.base.BaseViewModel
import ir.ah.app.foodlover.data.model.banner.Banner
import ir.ah.app.foodlover.data.model.category.Category
import ir.ah.app.foodlover.data.model.restaurant.Restaurant
import ir.ah.app.foodlover.data.remot.repositoeies.home.HomeRepositoryImpl
import ir.ah.app.foodlover.other.Resource
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepositoryImpl) :
    BaseViewModel() {

    private val _restaurant = MutableLiveData<Resource<List<Restaurant>>>()
    val restaurant: LiveData<Resource<List<Restaurant>>> = _restaurant
    private val _category = MutableLiveData<Resource<List<Category>>>()
    val category: LiveData<Resource<List<Category>>> = _category
    private val _banner = MutableLiveData<Resource<List<Banner>>>()
    val banner: LiveData<Resource<List<Banner>>> = _banner

    init {
        getAllRestaurant()
        getAllCategory()
        getAllBanner()
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

    fun getAllCategory() = viewModelScope.launch {
        _category.postValue(Resource.Loading())
        try {
            val response = repository.getAllCategory()
            if (!response.isNullOrEmpty()) {
                _category.postValue(Resource.Success(response))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _category.postValue(Resource.Error("Network Failure", null, 404))
                else -> _category.postValue(Resource.Error("Conversion Error", null, 404))
            }

        }
    }

    fun getAllBanner() = viewModelScope.launch {
        _banner.postValue(Resource.Loading())
        try {
            val response = repository.getAllBanner()
            if (!response.isNullOrEmpty()) {
                _banner.postValue(Resource.Success(response))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _banner.postValue(Resource.Error("Network Failure", null, 404))
                else -> _banner.postValue(Resource.Error("Conversion Error", null, 404))
            }

        }
    }


}