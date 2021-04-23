package ir.ah.app.foodlover.ui.fragment.introit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.ui.adapter.IntroSliderAdapter
import kotlinx.android.synthetic.main.fragment_introit.*

@AndroidEntryPoint
class IntroitFragment : Fragment(R.layout.fragment_introit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = IntroSliderAdapter()
        indicator.setViewPager(viewPager)
        btnNext.setOnClickListener {
            if (viewPager.currentItem ==1) {
                btnNext.visibility = View.GONE
                indicator.visibility = View.GONE
                btnSkip.visibility = View.VISIBLE
            }
            viewPager.apply {
                beginFakeDrag()
                fakeDragBy(-10f)
                endFakeDrag()
            }

        }

        btnSkip.setOnClickListener {
            Snackbar.make(requireView(), "navigate", Snackbar.LENGTH_LONG).show()
        }

    }


}