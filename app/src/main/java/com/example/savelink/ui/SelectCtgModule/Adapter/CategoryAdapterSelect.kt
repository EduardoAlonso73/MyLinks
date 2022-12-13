package com.example.savelink.ui.SelectCtgModule.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savelink.R
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.databinding.ItemSelectCategoryBinding
import com.example.savelink.utils.IOnCategorySelectListener


class CategoryAdapterSelect(private val listener: IOnCategorySelectListener) :
    ListAdapter<CategoryEnt, RecyclerView.ViewHolder>(StoreDiffCallback()) {
    private lateinit var mContext: Context
    private lateinit var binding: ItemSelectCategoryBinding
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_select_category, parent, false)
        binding = ItemSelectCategoryBinding.bind(view)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemLinks = getItem(position)
        with(holder as ViewHolder) {
            with(binding) {
                setListener(itemLinks)
                radioButton.text = itemLinks.category
                radioButton.isChecked = position == selectedPosition
            }
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSelectCategoryBinding.bind(view)
        fun setListener(categoryEnt: CategoryEnt) {
            binding.radioButton.setOnClickListener {
                selectedPosition=getAdapterPosition()
                notifyDataSetChanged()}

        }


    }


    class StoreDiffCallback : DiffUtil.ItemCallback<CategoryEnt>() {
        override fun areItemsTheSame(oldItem: CategoryEnt, newItem: CategoryEnt): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CategoryEnt, newItem: CategoryEnt): Boolean =
            oldItem == newItem
    }


}

