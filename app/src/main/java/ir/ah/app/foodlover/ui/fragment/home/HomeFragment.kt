package ir.ah.app.foodlover.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.BaseFragment
import ir.ah.app.foodlover.other.Constance
import ir.ah.app.foodlover.other.Resource
import ir.ah.app.foodlover.ui.adapter.BannerAdapter
import ir.ah.app.foodlover.ui.adapter.CategoryAdapter
import ir.ah.app.foodlover.ui.adapter.PopularAdapter
import ir.ah.app.foodlover.ui.adapter.RecommendedAdapter
import kotlinx.android.synthetic.main.fragemnt_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel>(R.layout.fragemnt_home, HomeViewModel::class) {
    @Inject
    lateinit var popularAdapter: PopularAdapter

    @Inject
    lateinit var recommendedAdapter: RecommendedAdapter

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

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
        txt_homeFragment_popularMore.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllRestaurantFragment())
        }
        txt_homeFragment_categoryMore.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCategoryFragment())
        }
        txt_homeFragment_recommendedMore.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllRestaurantFragment())
        }
        txt_homeFragment_bannerMore.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDiscountFragment())
        }
        fl_homeFragment_search.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }
        popularAdapter.setOnItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRestaurantFragment())
        }
        recommendedAdapter.setOnItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRestaurantFragment())
        }
        categoryAdapter.setOnItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllRestaurantFragment())
        }

    }

    private fun subscribeToObservers() {

        viewModel.restaurant.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        popularAdapter.differ.submitList(it)
                        recommendedAdapter.differ.submitList(it)

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
        viewModel.category.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        categoryAdapter.differ.submitList(it)
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
        viewModel.banner.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
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
        rv_homeFragment_popular.apply {
            adapter = popularAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        rv_homeFragment_recommended.apply {
            adapter = recommendedAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        rv_homeFragment_category.apply {
            adapter = categoryAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        rv_homeFragment_banner.apply {
            adapter = bannerAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }


}