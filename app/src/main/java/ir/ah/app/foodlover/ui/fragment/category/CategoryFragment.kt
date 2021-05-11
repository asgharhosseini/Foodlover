package ir.ah.app.foodlover.ui.fragment.category

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
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.*

@AndroidEntryPoint
class CategoryFragment : BaseFragment<CategoryViewModel>(R.layout.fragment_category, CategoryViewModel::class) {
    @Inject
    lateinit var categoryAdapter: AllCategoryAdapter


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

        categoryAdapter.setOnItemClickListener {
            findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToAllRestaurantFragment())
        }
        iv_fragmentCategory_backBottom.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun subscribeToObservers() {

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


    }

    private fun setUpRecyclerViews() {
        rv_fragmentCategory.apply {
            adapter = categoryAdapter
            layoutManager =
                    GridLayoutManager(requireContext(), 3)
        }

    }


}