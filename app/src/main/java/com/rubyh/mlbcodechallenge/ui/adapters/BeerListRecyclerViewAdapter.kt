package com.rubyh.mlbcodechallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rubyh.mlbcodechallenge.R
import com.rubyh.mlbcodechallenge.databinding.BeerListContentBinding
import com.rubyh.mlbcodechallenge.model.BeerDetails

class BeerListRecyclerViewAdapter(
    private val itemDetailFragmentContainer: View?,
    private val onBeerSelected: ((BeerDetails) -> Unit)? = null
) : ListAdapter<BeerDetails, BeerListRecyclerViewAdapter.ViewHolder>(BeerDetailsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BeerListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(val binding: BeerListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.beerDetails?.let { beer ->
                    onBeerSelected?.invoke(beer)
                    val bundle = Bundle()
                    bundle.putInt(
                        BeerDetailFragment.ITEM_ID,
                        beer.id
                    )
                    if (itemDetailFragmentContainer != null) {
                        itemDetailFragmentContainer.findNavController()
                            .navigate(R.id.fragment_beer_detail, bundle)
                    } else {
                        itemView.findNavController().navigate(R.id.show_beer_detail, bundle)
                    }

                }
            }
        }

        fun bind(item: BeerDetails) {
            binding.beerDetails = item
            binding.executePendingBindings()
        }
    }

}

class BeerDetailsDiffCallback : DiffUtil.ItemCallback<BeerDetails>() {

    override fun areItemsTheSame(oldItem: BeerDetails, newItem: BeerDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BeerDetails, newItem: BeerDetails): Boolean {
        return oldItem == newItem
    }
}