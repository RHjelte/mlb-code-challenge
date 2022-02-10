package com.rubyh.mlbcodechallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rubyh.mlbcodechallenge.databinding.FragmentBeerDetailBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class BeerDetailFragment : Fragment() {

    private val viewModel by sharedViewModel<BeerViewModel>()

    private var _binding: FragmentBeerDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ITEM_ID = "item_id"
    }
}
