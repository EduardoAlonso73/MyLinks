package com.example.savelink.utils

import android.content.Context
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
    var isLoading: Boolean = true
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
                linkSiteName.text = openGraphResult.siteName
            }
            Glide.with(context)
                .load(openGraphResult.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.ic_error_img)
                .placeholder(R.drawable.ic_load)
                .into(binding.linkImage)
            isLoading = false
        }



    }

    override fun onError(error: String) {}
}