package com.example.savelink.ui.addCategory

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.databinding.FragmentAddCategoryBinding


class AddCategory : DialogFragment() {

    private  var binding: FragmentAddCategoryBinding?=null
    private   val mAddCategoryViewModel by lazy { ViewModelProvider(requireActivity())[AddCategoryViewModel::class.java] }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddCategoryBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        binding?.let { builder.setView(it.root) }

        addCategory()
        return builder.create()
            .apply { window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }

    }

    private fun addCategory() {
        binding?.let {binding->
            binding.btnAddCategory.setOnClickListener {
                val newCategory=CategoryEnt(category =binding.etCategory.text.toString().trim() )
                mAddCategoryViewModel.saveCategory(newCategory)
                dismiss()

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


}