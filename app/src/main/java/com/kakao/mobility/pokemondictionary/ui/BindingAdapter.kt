package com.kakao.mobility.pokemondictionary.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) =
        url?.let { Glide.with(view.context).load(url).into(view) }
}