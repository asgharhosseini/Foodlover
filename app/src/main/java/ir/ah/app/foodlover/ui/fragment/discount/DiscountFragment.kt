package ir.ah.app.foodlover.ui.fragment.discount

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
import kotlinx.android.synthetic.main.fragment_discount.*
import javax.inject.*

@AndroidEntryPoint
class DiscountFragment : BaseFragment<DiscountViewModel>(R.layout.fragment_discount, DiscountViewModel::class) {

    @Inject
    lateinit var bannerAdapter: BannerAdapter


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
        iv_fragmentDiscount_backBottom.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun subscribeToObservers() {


        viewModel.banner.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        bannerAdapter.differ.submitList(it)
                        bannerAdapter.differ.submitList(it)
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

        rv_fragmentDiscount.apply {
            adapter = bannerAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


}