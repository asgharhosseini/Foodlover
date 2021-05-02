package ir.ah.app.foodlover.ui.fragment.allorder

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.BaseFragment
import ir.ah.app.foodlover.data.model.order.Order
import ir.ah.app.foodlover.other.NumberHelper
import ir.ah.app.foodlover.ui.adapter.OrderAdapter
import ir.ah.app.foodlover.ui.fragment.restaurant.RestaurantViewModel
import kotlinx.android.synthetic.main.fragment_all_order.*
import javax.inject.Inject

@AndroidEntryPoint
class AllOrderFragment :
    BaseFragment<RestaurantViewModel>(R.layout.fragment_all_order, RestaurantViewModel::class),
    OrderAdapter.OrderEventListener {

    @Inject
    lateinit var orderAdapter: OrderAdapter
    var map: GoogleMap? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        txt_fragmentOrder_gift.text = NumberHelper.EnglishToPersian("0") + "%"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_ios_new_24)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

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
        viewModel.totalSum.observe(viewLifecycleOwner, Observer {
            txt_fragmentOrder_totalPrice.text =
                NumberHelper.EnglishToPersian(it.toString()) + "تومان"
        })
    }


    private fun setUpRecyclerViews() {
        rv_fragmentAllOrder.apply {
            adapter = orderAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        orderAdapter.setOnOrderItemEventListener(this)

    }


    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onDelete(order: Order, position: Int) {
        viewModel.deleteOrderItem(order)
    }


}

