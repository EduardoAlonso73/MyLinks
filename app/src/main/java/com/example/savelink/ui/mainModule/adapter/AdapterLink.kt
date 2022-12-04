package com.example.savelink.ui.addModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.savelink.R
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.databinding.ItemLinkBinding
import com.example.savelink.utils.IOnClickListener


class AdapterLink(private var mLinks:MutableList<LinkEnt>, private val listener: IOnClickListener)
    : RecyclerView.Adapter<AdapterLink.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_link,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val itemLinks=mLinks[position]
        with(holder){
            with(binding){
                setListener(itemLinks)
               /* pwUrl.setURL(itemLinks.link) { data ->
                    pwUrl.title(data.title)
                    pwUrl.description(data.description)
                    pwUrl.host(data.host)
                    pwUrl.thumbnail(data.thumbnailURL)
                    pwUrl.favor(data.favorURL)
                }*/

            }
        }
    }

    fun add(list: MutableList<LinkEnt>){
        this.mLinks=list
        //notifyDataSetChanged()
        notifyItemChanged(mLinks.size-1)

    }

    override fun getItemCount(): Int = mLinks.size


    inner class  ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding=ItemLinkBinding.bind(view)
        fun setListener(saveLink: LinkEnt){
            binding.root.setOnClickListener { listener.onClick(saveLink) }
            //binding.cbDelete.setOnClickListener{ listener.onLongClick(itemEntity)
        }
    }
}


