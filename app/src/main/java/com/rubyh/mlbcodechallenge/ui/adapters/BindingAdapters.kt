package com.rubyh.mlbcodechallenge.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("urlForImage")
fun bindUrlForImage(view: ImageView, imageUrl: String?) {
    imageUrl?.also {
        view.load(it)
    }
}