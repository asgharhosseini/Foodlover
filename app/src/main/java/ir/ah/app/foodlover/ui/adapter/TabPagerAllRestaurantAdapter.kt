package ir.ah.app.foodlover.ui.adapter

import androidx.fragment.app.*
import ir.ah.app.foodlover.ui.fragment.allrestaurant.featured.*
import ir.ah.app.foodlover.ui.fragment.allrestaurant.newest.*
import ir.ah.app.foodlover.ui.fragment.allrestaurant.popular.*
import ir.ah.app.foodlover.ui.fragment.allrestaurant.trending.*

class TabPagerAllRestaurantAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FeaturedFragment()
            1 -> NewestFragment()
            2 -> PopularFragment()
            3 -> TrendingFragment()
            else -> getItem(position)

        }

    }

    override fun getCount(): Int {
        return tabCount
    }
}