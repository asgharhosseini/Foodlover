package ir.ah.app.foodlover.ui.fragment.restaurant

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.BaseFragment
import ir.ah.app.foodlover.data.model.order.Order
import ir.ah.app.foodlover.other.Constance
import ir.ah.app.foodlover.other.NumberHelper
import ir.ah.app.foodlover.other.Resource
import ir.ah.app.foodlover.ui.adapter.MainCourseAdapter
import ir.ah.app.foodlover.ui.adapter.TabPagerMenuAdapter
import ir.ah.app.foodlover.ui.dialog.OrderDialog
import kotlinx.android.synthetic.main.fragment_restaurant.*
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantFragment : BaseFragment<RestaurantViewModel>(R.layout.fragment_restaurant, RestaurantViewModel::class) {
    @Inject
    lateinit var dailyAdapter: MainCourseAdapter

    @Inject
    lateinit var glide: RequestManager

    var title = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_ios_new_24)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        configureTabLayout()
        subscribeToObservers()
        setUpRecyclerViews()
        onClick()
        handleCollapsedToolbarTitle()

    }

    private fun configureTabLayout() {

        tabLayout.addTab(tabLayout.newTab().setText("پیش غذا"))
        tabLayout.addTab(tabLayout.newTab().setText("غدا اصلی"))
        tabLayout.addTab(tabLayout.newTab().setText("دسر"))
        tabLayout.addTab(tabLayout.newTab().setText("نوشیدنی"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabPagerMenuAdapter(parentFragmentManager, tabLayout.tabCount)
        vp_fragmentRestaurant.adapter = adapter
        vp_fragmentRestaurant.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vp_fragmentRestaurant.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}

        })

        tabLayout_top.addTab(tabLayout_top.newTab().setText("رستوران"))
        tabLayout_top.addTab(tabLayout_top.newTab().setText("منو"))
        tabLayout_top.addTab(tabLayout_top.newTab().setText("بازدید"))

        tabLayout_top.tabGravity = TabLayout.GRAVITY_FILL


    }

    private fun onClick() {

        dailyAdapter.setOnItemClickListener {
            val dialog = OrderDialog(Order(it.id, it.title, it.image, it.price),
                onClicked = {
                    Snackbar.make(requireView(), this.title, Snackbar.LENGTH_LONG).show()

                })
            dialog.show(parentFragmentManager, null)
        }
        btn_allOrder.setOnClickListener {
            findNavController().navigate(RestaurantFragmentDirections.actionRestaurantFragmentToAllOrderFragment())
        }

    }

    private fun subscribeToObservers() {
/**/
        viewModel.restaurant.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        dailyAdapter.differ.submitList(it.mainCourse)
                        glide.load(it.image).into(app_bar_image)
                        title = " رستوران " + it.title
                        txt_fragmentRestaurant_title.text = "رستوران : " + it.title
                        txt_fragmentRestaurant_category.text = " دسته بندی : " + it.categories
                        chip_fragmentRestaurant_distanceTime.text = it.timeDistance
                        chip_fragmentRestaurant_distance.text = it.distance

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

        viewModel.totalCount.observe(viewLifecycleOwner, Observer {
            btn_allOrder.text = "سفارشات شما (${NumberHelper.EnglishToPersian(it.toString())})"
        })


    }

    private fun handleCollapsedToolbarTitle() {
        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout!!.totalScrollRange
                }
                // verify if the toolbar is completely collapsed and set the movie name as the title
                // verify if the toolbar is completely collapsed and set the movie name as the title
                if (scrollRange + verticalOffset == 0) {
                    toolbarTitle.text = title
                    isShow = true
                } else if (isShow) {
                    // display an empty string when toolbar is expanded
                    toolbarTitle.text = " "
                    isShow = false
                }
            }
        })
    }

    private fun setUpRecyclerViews() {
        rv_fragmentRestaurant_daily.apply {
            adapter = dailyAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
        }

    }

}