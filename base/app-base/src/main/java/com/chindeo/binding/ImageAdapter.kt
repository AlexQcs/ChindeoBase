package com.chindeo.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 *   @auther : Aleyn
 *   time   : 2019/11/07
 */
object ImageAdapter {

    @BindingAdapter(value = ["imageUrl"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView).load(imageUrl).into(imageView)
        }
    }

}