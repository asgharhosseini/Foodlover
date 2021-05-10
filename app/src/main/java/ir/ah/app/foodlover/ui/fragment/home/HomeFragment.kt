package ir.ah.app.foodlover.ui.fragment.home

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
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel>(R.layout.fragment_home, HomeViewModel::class) {
    @Inject
    lateinit var popularAdapter: PopularAdapter

    @Inject
    lateinit var recommendedAdapter: RecommendedAdapter

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var bannerAdapter: BannerAdapter

    @Inject
    lateinit var userInfoManager: UserInfoManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        subscribeToObservers()
        setUpRecyclerViews()
        onClick()

        txt_fragmentHome_addresses.text = userInfoManager.getLocationName().toString().trim()

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