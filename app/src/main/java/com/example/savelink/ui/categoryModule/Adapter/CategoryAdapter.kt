package com.example.savelink.ui.categoryModule.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savelink.R
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.databinding.ItemCategoryBinding
import com.example.savelink.utils.IOnCategoryListener



class CategoryAdapter(private val listener: IOnCategoryListener) :
    ListAdapter<CategoryEnt, RecyclerView.ViewHolder>(StoreDiffCallback()) {
    private lateinit var mContext: Context
    private lateinit var binding: ItemCategoryBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false)
        binding = ItemCategoryBinding.bind(view)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemLinks = getItem(position)
        with(holder as ViewHolder) {
            with(binding) {
                setListener(itemLinks)
                binding.chipChat.text = itemLinks.category

            }

        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCategoryBinding.bind(view)
        fun setListener(categoryEnt: CategoryEnt) {
            binding.chipChat.setOnClickListener { listener.onClickCategory(categoryEnt) }

        }
    }


    class StoreDiffCallback : DiffUtil.ItemCallback<CategoryEnt>() {
        override fun areItemsTheSame(oldItem: CategoryEnt, newItem: CategoryEnt): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CategoryEnt, newItem: CategoryEnt): Boolean =
            oldItem == newItem
    }


}

