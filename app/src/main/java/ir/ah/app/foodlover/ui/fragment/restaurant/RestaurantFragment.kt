package ir.ah.app.foodlover.ui.fragment.restaurant

import android.os.*
import android.view.*
import androidx.fragment.app.*
import com.google.android.material.tabs.*
import ir.ah.app.foodlover.*
import ir.ah.app.foodlover.ui.adapter.*
import kotlinx.android.synthetic.main.fragment_restaurant.*

class RestaurantFragment : Fragment(R.layout.fragment_restaurant) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {
        configureTabLayout()
    }

    private fun configureTabLayout() {

        tabLayout.addTab(tabLayout.newTab().setText("پیش غذا"))
        tabLayout.addTab(tabLayout.newTab().setText("غدا اصلی"))
        tabLayout.addTab(tabLayout.newTab().setText("دسر"))
        tabLayout.addTab(tabLayout.newTab().setText("نوشیدنی"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabPagerAdapter(parentFragmentManager, tabLayout.tabCount)
        vp_fragmentRestaurant.adapter = adapter
        vp_fragmentRestaurant.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vp_fragmentRestaurant.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}

        })
    }
}