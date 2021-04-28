package ir.ah.app.foodlover.ui.fragment.allrestaurant

import android.os.*
import android.view.*
import com.google.android.material.tabs.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.ui.adapter.*
import kotlinx.android.synthetic.main.fragment_all_restaurant.*


import javax.inject.*

@AndroidEntryPoint
class AllRestaurantFragment : BaseFragment<AllRestaurantViewModel>(R.layout.fragment_all_restaurant, AllRestaurantViewModel::class) {
    @Inject
    lateinit var allRestaurantAdapter: AllRestaurantAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        configureTabLayout()
    }

    private fun configureTabLayout() {

        tabLayout.addTab(tabLayout.newTab().setText("ویژه"))
        tabLayout.addTab(tabLayout.newTab().setText("محبوب"))
        tabLayout.addTab(tabLayout.newTab().setText("جدید"))
        tabLayout.addTab(tabLayout.newTab().setText("برتر"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabPagerAllRestaurantAdapter(parentFragmentManager, tabLayout.tabCount)
        vp_fragmentAllRestaurant.adapter = adapter
        vp_fragmentAllRestaurant.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vp_fragmentAllRestaurant.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}

        })
    }


}