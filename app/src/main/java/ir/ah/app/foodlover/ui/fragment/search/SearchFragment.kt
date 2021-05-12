package ir.ah.app.foodlover.ui.fragment.search

import android.os.*
import android.util.*
import android.view.*
import androidx.appcompat.widget.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.base.*
import ir.ah.app.foodlover.other.*
import ir.ah.app.foodlover.ui.adapter.*
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.*

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search, SearchViewModel::class) {

    @Inject
    lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        setupRecyclerView()
        setupSearchView()
        onClick()
    }

    private fun onClick() {
        searchAdapter.setOnItemClickListener {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToRestaurantFragment(
                    it.id
                )
            )
        }
    }

    fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                txt_searchHelper_fragmentSearch.visibility = View.GONE
                lottieAnimationView.visibility = View.GONE
                if (newText.length == 0) {
                    txt_searchHelper_fragmentSearch.visibility = View.VISIBLE
                    lottieAnimationView.visibility = View.VISIBLE
                    searchAdapter.differ.submitList(listOf())

                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearch(query)
                txt_searchHelper_fragmentSearch.visibility = View.GONE
                lottieAnimationView.visibility = View.GONE
                subscribeToObservers()
                return false
            }
        })
        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                searchAdapter.differ.submitList(listOf())
                txt_searchHelper_fragmentSearch.visibility = View.VISIBLE
                lottieAnimationView.visibility = View.VISIBLE
                return false
            }
        })

    }


    private fun setupRecyclerView() {
        rv_fragmentSearch_search.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun subscribeToObservers() {
        viewModel.SearchRestaurant.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        searchAdapter.differ.submitList(it)
                    }
                }
                is Resource.Error -> {

                    result.message?.let { message ->
                        Log.e(Constance.TAG, message)
                        Snackbar.make(requireView(), "Error: $message ${result.code}", Snackbar.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {

                }
            }
        })
    }


}