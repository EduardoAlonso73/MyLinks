package com.example.savelink.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.savelink.R
import com.example.savelink.databinding.ItemLinkBinding
import com.kedia.ogparser.OpenGraphCacheProvider
import com.kedia.ogparser.OpenGraphCallback
import com.kedia.ogparser.OpenGraphParser
import com.kedia.ogparser.OpenGraphResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuxOpenGraphCallback(val context: Context, val binding: ItemLinkBinding) : OpenGraphCallback {
    val openGraphParser by lazy {
        OpenGraphParser(
            listener = this,
            showNullOnEmpty = false,
            cacheProvider = OpenGraphCacheProvider(context)
        )
    }

    override fun onPostResponse(openGraphResult: OpenGraphResult) {
        CoroutineScope(Dispatchers.Main).launch {
            with(binding) {
                linkTitle.text = openGraphResult.title
                linkDescription.text = openGraphResult.description

            }
            Glide.with(context)
                .asBitmap()
                .load(openGraphResult.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(object:CustomTarget<Bitmap>(90,110){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                       binding.linkImage.setImageBitmap(resource)
                        binding.viewLoading.visibility=View.INVISIBLE
                        binding.card.visibility=View.VISIBLE
                    }

                    override fun onLoadStarted(placeholder: Drawable?) {
                        binding.linkImage.setImageResource(R.drawable.ic_load)
                        binding.viewLoading.visibility=View.INVISIBLE
                        binding.card.visibility=View.VISIBLE
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        binding.linkImage.setImageResource(R.drawable.ic_error_img)
                        binding.viewLoading.visibility=View.INVISIBLE
                        binding.card.visibility=View.VISIBLE
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        binding.linkImage.setImageResource(R.drawable.ic_load)
                    }


                })

        }



    }

    override fun onError(error: String) {}
}