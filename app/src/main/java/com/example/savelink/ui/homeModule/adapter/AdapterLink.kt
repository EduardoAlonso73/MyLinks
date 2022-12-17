package com.example.savelink.ui.addLinkModule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.savelink.R
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.databinding.ItemLinkBinding
import com.example.savelink.utils.AuxOpenGraphCallback
import com.example.savelink.utils.IOnClickListener


class AdapterLink(private var mLinks:MutableList<LinkEnt>, private val listener: IOnClickListener)
    : RecyclerView.Adapter<AdapterLink.ViewHolder>() {
    private lateinit var mContext: Context
    private var selectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_link, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val itemLinks = mLinks[position]
        with(holder) {
            with(binding) {
                setListener(itemLinks)
                binding.card.visibility=View.INVISIBLE
                binding.viewLoading.visibility=View.VISIBLE
                AuxOpenGraphCallback(mContext, binding).apply {
                    openGraphParser.parse(itemLinks.link)
                    cbIsFavorite.isChecked=itemLinks.isFavorite
                }
            }
        }
    }
    fun setList(list: MutableList<LinkEnt>){
        this.mLinks=list
       notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mLinks.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLinkBinding.bind(view)
        fun setListener(linkEnt: LinkEnt) {
            binding.root.setOnClickListener { listener.onClick(linkEnt) }
            binding.cbIsFavorite.setOnClickListener { listener.isFavorite(linkEnt) }
            binding.ibShareLink.setOnClickListener { listener.shareLink(linkEnt.link) }
            binding.ibRemove.setOnClickListener{ listener.removeLink(linkEnt) }
            binding.ibBookAdd.setOnClickListener { listener.bookAdA(linkEnt) }
            binding.root.setOnLongClickListener{ listener.copiClipboard(linkEnt.link)
                true}
        }
    }

}


