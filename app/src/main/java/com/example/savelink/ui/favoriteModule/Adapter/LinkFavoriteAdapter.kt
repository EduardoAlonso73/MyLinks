package com.example.savelink.ui.favoriteModule.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savelink.R
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.databinding.ItemLinkBinding
import com.example.savelink.utils.AuxOpenGraphCallback
import com.example.savelink.utils.IOnClickListener


class LinkFavoriteAdapter(private var listener: IOnClickListener) :
    ListAdapter<LinkEnt, RecyclerView.ViewHolder>(StoreDiffCallback()) { 
    private lateinit var mContext: Context
    private lateinit var binding: ItemLinkBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_link, parent, false)
        binding = ItemLinkBinding.bind(view)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemLinksFavorite = getItem(position)
        with(holder as ViewHolder) {
            with(binding) {
                setListener(itemLinksFavorite)
                binding.card.visibility=View.INVISIBLE
                binding.viewLoading.visibility=View.VISIBLE
                AuxOpenGraphCallback(mContext, binding).apply {
                    openGraphParser.parse(itemLinksFavorite.link)
                    cbIsFavorite.isChecked=itemLinksFavorite.isFavorite
                }
            }
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLinkBinding.bind(view)
        fun setListener(linkEnt: LinkEnt) {
            binding.root.setOnClickListener { listener.onClick(linkEnt) }
            binding.cbIsFavorite.setOnClickListener { listener.isFavorite(linkEnt) }
            binding.ibShareLink.setOnClickListener { listener.shareLink(linkEnt.link) }
            binding.ibRemove.setOnClickListener{ listener.removeLink(linkEnt) }
            binding.root.setOnLongClickListener{ listener.copiClipboard(linkEnt.link)
                true}
        }
    }


    class StoreDiffCallback : DiffUtil.ItemCallback<LinkEnt>() {
        override fun areItemsTheSame(oldItem: LinkEnt, newItem: LinkEnt): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LinkEnt, newItem: LinkEnt): Boolean =
            oldItem == newItem
    }


}

