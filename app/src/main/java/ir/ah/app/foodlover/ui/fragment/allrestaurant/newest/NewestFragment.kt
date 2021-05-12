package ir.ah.app.foodlover.ui.fragment.allrestaurant.newest

import android.os.*
import android.util.*
import android.view.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.other.*
import ir.ah.app.foodlover.ui.adapter.*
import ir.ah.app.foodlover.ui.fragment.allrestaurant.*
import kotlinx.android.synthetic.main.fragment_newest.*


import javax.inject.*

@AndroidEntryPoint
class NewestFragment : BaseFragment<AllRestaurantViewModel>(R.layout.fragment_newest, AllRestaurantViewModel::class) {


    @Inject
    lateinit var allRestaurantAdapter: AllRestaurantAdapter


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

        allRestaurantAdapter.setOnItemClickListener {
            findNavController().navigate(
                AllRestaurantFragmentDirections.actionAllRestaurantFragmentToRestaurantFragment(
                    it.id
                )
            )
        }

    }

    private fun subscribeToObservers() {

        viewModel.restaurant.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        allRestaurantAdapter.differ.submitList(it)
                        allRestaurantAdapter.differ.submitList(it)

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
        rv_fragmentNewest.apply {
            adapter = allRestaurantAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }


}