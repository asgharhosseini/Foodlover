package ir.ah.app.foodlover.ui.fragment.restaurant.appetizer

import android.os.*
import android.util.*
import android.view.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.other.*
import ir.ah.app.foodlover.ui.adapter.*
import ir.ah.app.foodlover.ui.fragment.restaurant.*
import kotlinx.android.synthetic.main.fragment_appetizer.*
import javax.inject.*

@AndroidEntryPoint
class AppetizerFragment : BaseFragment<RestaurantViewModel>(R.layout.fragment_appetizer, RestaurantViewModel::class) {

    @Inject
    lateinit var appetizerAdapter: AppetizerAdapter


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

        appetizerAdapter.setOnItemClickListener {
        }

    }

    private fun subscribeToObservers() {
/**/
        viewModel.restaurant.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        appetizerAdapter.differ.submitList(it.appetizer)
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
        rv_fragmentAppetizer.apply {
            adapter = appetizerAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }


}