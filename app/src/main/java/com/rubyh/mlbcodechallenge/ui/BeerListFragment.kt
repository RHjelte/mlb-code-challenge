package com.rubyh.mlbcodechallenge.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.rubyh.mlbcodechallenge.R
import com.rubyh.mlbcodechallenge.databinding.FragmentBeerListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BeerListFragment : Fragment() {

    private val viewModel by sharedViewModel<BeerViewModel>()

    private var _binding: FragmentBeerListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        _binding = FragmentBeerListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.beerList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.beer_detail_nav_container)


        val adapter = BeerListRecyclerViewAdapter(
            itemDetailFragmentContainer
        ) {
            viewModel.selectedBeer = it
        }

        recyclerView.adapter = adapter

        viewModel.beersState.observe(requireActivity(), Observer { state ->
            when (state) {
                is BeersState.Loading -> {
                    adapter.submitList(emptyList())
                    showWaitIndicator(true)
                }
                is BeersState.LoadComplete -> {
                    adapter.submitList(state.beerList)
                    showWaitIndicator(false)
                }
                is BeersState.Error -> {
                    showWaitIndicator(false)
                    showErrorMessage(state.message)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_options_search, menu)
        val searchView = (menu.findItem(R.id.search).actionView as SearchView)
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.loadBeers(query)
                // Collapse and iconify the search view
                searchView.isIconified = true
                searchView.onActionViewCollapsed()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showWaitIndicator(show: Boolean) {
        binding.wait.visibility =
            if (show) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, message, LENGTH_LONG)
            .show()
    }
}
