package ir.ah.app.foodlover.ui.fragment.restaurant.maincourse

import android.os.*
import android.util.*
import android.view.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.data.model.order.*
import ir.ah.app.foodlover.other.*
import ir.ah.app.foodlover.ui.adapter.*
import ir.ah.app.foodlover.ui.dialog.*
import ir.ah.app.foodlover.ui.fragment.restaurant.*
import kotlinx.android.synthetic.main.fragment_maincourse.*
import javax.inject.*

@AndroidEntryPoint
class MainCourseFragment : BaseFragment<RestaurantViewModel>(R.layout.fragment_maincourse, RestaurantViewModel::class) {

    @Inject
    lateinit var mainCourseAdapter: MainCourseAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewModel.getRestaurant()
        subscribeToObservers()
        setUpRecyclerViews()
        onClick()

    }

    private fun onClick() {

        mainCourseAdapter.setOnItemClickListener {
            val dialog = OrderDialog(Order(it.id, it.title, it.image, it.price),
                onClicked = {
                    Snackbar.make(requireView(), this.title, Snackbar.LENGTH_LONG).show()
                    for (i in 1..this.count) {
                        viewModel.insertOrderItemIntoDb(this)
                    }
                })
            dialog.show(parentFragmentManager, null)
        }

    }

    private fun subscribeToObservers() {
/**/
        viewModel.restaurant.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        mainCourseAdapter.differ.submitList(it.mainCourse)
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
        rv_fragmentMainCourse.apply {
            adapter = mainCourseAdapter
            layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }


}