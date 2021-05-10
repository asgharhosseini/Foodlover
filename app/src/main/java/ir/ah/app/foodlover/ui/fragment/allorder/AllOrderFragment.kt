package ir.ah.app.foodlover.ui.fragment.allorder

import android.os.*
import android.view.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import com.google.android.gms.maps.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.data.model.order.*
import ir.ah.app.foodlover.other.*
import ir.ah.app.foodlover.ui.adapter.*
import ir.ah.app.foodlover.ui.fragment.restaurant.*
import kotlinx.android.synthetic.main.fragment_all_order.*
import javax.inject.*

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

            if (it != null) {
                txt_fragmentOrder_totalPrice.text =
                    NumberHelper.EnglishToPersian(it.toString()) + "تومان"
            } else {
                txt_fragmentOrder_totalPrice.text =
                    NumberHelper.EnglishToPersian("0") + "تومان"
                findNavController().popBackStack()
            }

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

