package ir.ah.app.foodlover.ui.fragment.restaurant.beverages

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.BaseFragment
import ir.ah.app.foodlover.data.model.order.Order
import ir.ah.app.foodlover.other.Constance
import ir.ah.app.foodlover.other.Resource
import ir.ah.app.foodlover.ui.adapter.BeveragesAdapter
import ir.ah.app.foodlover.ui.dialog.OrderDialog
import ir.ah.app.foodlover.ui.fragment.restaurant.RestaurantViewModel
import kotlinx.android.synthetic.main.fragment_beverages.*
import javax.inject.Inject

@AndroidEntryPoint
class BeveragesFragment : BaseFragment<RestaurantViewModel>(R.layout.fragment_beverages, RestaurantViewModel::class) {

    @Inject
    lateinit var beveragesAdapter: BeveragesAdapter


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

        beveragesAdapter.setOnItemClickListener {
            val dialog = OrderDialog(Order(it.id, it.title, it.image, it.price),
                onClicked = {
                    Snackbar.make(requireView(), this.title, Snackbar.LENGTH_LONG).show()

                })
            dialog.show(parentFragmentManager, null)
        }

    }

    private fun subscribeToObservers() {
/**/
        viewModel.restaurant.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        beveragesAdapter.differ.submitList(it.beverages)
                    }
                }
                is Resource.Error -> {

                    result.message?.let { message ->
                        Log.e(Constance.TAG, message)
                        Snackbar.make(
                                requireView(),
                                "Error: $message ${result.code}",
                                Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                is Resource.Loading -> Unit
            }
        })


    }

    private fun setUpRecyclerViews() {
        rv_fragmentBeverages.apply {
            adapter = beveragesAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }


}