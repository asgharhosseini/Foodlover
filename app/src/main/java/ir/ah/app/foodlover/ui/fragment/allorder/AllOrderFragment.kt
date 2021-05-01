package ir.ah.app.foodlover.ui.fragment.allorder

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.BaseFragment
import ir.ah.app.foodlover.ui.adapter.OrderAdapter
import ir.ah.app.foodlover.ui.fragment.restaurant.RestaurantViewModel
import kotlinx.android.synthetic.main.fragment_all_order.*
import javax.inject.Inject

@AndroidEntryPoint
class AllOrderFragment :
    BaseFragment<RestaurantViewModel>(R.layout.fragment_all_order, RestaurantViewModel::class) {

    @Inject
    lateinit var orderAdapter: OrderAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

        subscribeToObservers()
        setUpRecyclerViews()
        onClick()


    }


    private fun onClick() {

    }

    private fun subscribeToObservers() {
        viewModel.allOrderItems.observe(viewLifecycleOwner, Observer {
            orderAdapter.differ.submitList(it)
        })
    }


    private fun setUpRecyclerViews() {
        rv_fragmentAllOrder.apply {
            adapter = orderAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

}