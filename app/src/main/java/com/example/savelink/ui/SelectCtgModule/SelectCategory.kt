package com.example.savelink.ui.SelectCtgModule

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.databinding.FragmentSelectCategoryBinding
import com.example.savelink.ui.SelectCtgModule.Adapter.CategoryAdapterSelect
import com.example.savelink.ui.addCategory.AddCategory
import com.example.savelink.ui.addCategory.AddCategoryViewModel
import com.example.savelink.ui.addModule.AddDialogLinkFragment
import com.example.savelink.ui.mainModule.mainViewModel.GetLinkViewModel
import com.example.savelink.utils.IOnCategorySelectListener

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectCategory : BottomSheetDialogFragment(), IOnCategorySelectListener {
    private lateinit var mAdapterCategorySelect: CategoryAdapterSelect
    private lateinit var mGridLayout: GridLayoutManager
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var selectLink:LinkEnt
    private val viewModelProvide: GetLinkViewModel by lazy {  ViewModelProvider(requireActivity())[GetLinkViewModel::class.java] }
    private   val categoryViewModel by lazy { ViewModelProvider(requireActivity())[AddCategoryViewModel::class.java] }
    private var mBinding: FragmentSelectCategoryBinding? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = FragmentSelectCategoryBinding.inflate(LayoutInflater.from(activity))
        mBinding?.let { fragmentCart ->
            val bottomSheetDialog = (super.onCreateDialog(savedInstanceState) as BottomSheetDialog)
                .apply { setContentView(fragmentCart.root) }
            bottomSheetBehavior = BottomSheetBehavior.from(fragmentCart.root.parent as View)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            setupRecyclerView()
            setupViewModelGetCategory()
            setupBtn()
            return bottomSheetDialog
        }
        return super.onCreateDialog(savedInstanceState)
    }




    private  fun setupViewModelGetCategory(){
        viewModelProvide.getListCategory().observe(requireActivity()){
            mAdapterCategorySelect.submitList(it)

        }
        viewModelProvide.link.observe(requireActivity()){
            selectLink=it
        }
    }

    private fun setupBtn() {
        mBinding?.let {
            //TODO:Here i will do the query to add in the database
            it.btnNewCategory.setOnClickListener {
                AddCategory().show(parentFragmentManager, AddCategory::class.java.simpleName)
                dismiss()
            }
        }

    }

    private fun setupRecyclerView() {

        mBinding?.let {
            mAdapterCategorySelect = CategoryAdapterSelect(this)
            mGridLayout = GridLayoutManager(requireContext(), 2)
            it.recyclerview.apply {
                layoutManager = mGridLayout
                adapter = mAdapterCategorySelect
            }
        }

    }

    override fun onSelectCategory(categoryEnt: CategoryEnt) {
        selectLink.category=categoryEnt.category
        viewModelProvide.updateLinkCategoty(selectLink)
        categoryViewModel.updateCheckedCt(categoryEnt)


    }


}

