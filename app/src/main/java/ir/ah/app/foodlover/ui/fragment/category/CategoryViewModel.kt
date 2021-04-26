package ir.ah.app.foodlover.ui.fragment.category

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.data.model.category.*
import ir.ah.app.foodlover.data.remot.repositoeies.home.*
import ir.ah.app.foodlover.other.*
import kotlinx.coroutines.*
import java.io.*
import javax.inject.*

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: HomeRepositoryImpl) : BaseViewModel() {
    private val _category = MutableLiveData<Resource<List<Category>>>()
    val category: LiveData<Resource<List<Category>>> = _category

    init {
        getAllCategory()

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
}