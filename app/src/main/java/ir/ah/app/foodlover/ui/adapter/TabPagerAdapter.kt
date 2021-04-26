package ir.ah.app.foodlover.ui.adapter

import androidx.fragment.app.*
import ir.ah.app.foodlover.ui.fragment.restaurant.appetizer.*
import ir.ah.app.foodlover.ui.fragment.restaurant.beverages.*
import ir.ah.app.foodlover.ui.fragment.restaurant.dessert.*
import ir.ah.app.foodlover.ui.fragment.restaurant.maincourse.*

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AppetizerFragment()
            1 -> MainCourseFragment()
            2 -> DessertFragment()
            3 -> BeveragesFragment()
            else -> getItem(position)

        }

    }

    override fun getCount(): Int {
        return tabCount
    }
}