package ir.ah.app.foodlover.base

import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.reflect.KClass

abstract class BaseFragment<viewModel : BaseViewModel>(
    @LayoutRes val layoutRes: Int,
    private val vmClass: KClass<viewModel>
) : Fragment(layoutRes) {
    @MainThread
    private fun viewModels(
        ownerProducer: () -> ViewModelStoreOwner = { this },
        factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ) = createViewModelLazy(vmClass, { ownerProducer().viewModelStore }, factoryProducer)

    open fun getViewModelStoreOwner(): ViewModelStoreOwner = this


    protected val viewModel: viewModel by viewModels({ getViewModelStoreOwner() })
}