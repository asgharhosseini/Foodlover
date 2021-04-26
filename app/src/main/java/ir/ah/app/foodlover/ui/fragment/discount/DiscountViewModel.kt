package ir.ah.app.foodlover.ui.fragment.discount

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.data.model.banner.*
import ir.ah.app.foodlover.data.remot.repositoeies.home.*
import ir.ah.app.foodlover.other.*
import kotlinx.coroutines.*
import java.io.*
import javax.inject.*

@HiltViewModel
class DiscountViewModel @Inject constructor(private val repository: HomeRepositoryImpl) : BaseViewModel() {
    private val _banner = MutableLiveData<Resource<List<Banner>>>()
    val banner: LiveData<Resource<List<Banner>>> = _banner

    init {

        getAllBanner()
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