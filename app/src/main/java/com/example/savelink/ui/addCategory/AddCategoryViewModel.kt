package com.example.savelink.ui.addCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savelink.damain.AddCategoryCaseUsen
import com.example.savelink.damain.SaveLinkCaseUsen
import com.example.savelink.damain.UpdateCheckedCategory
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.launch

class AddCategoryViewModel:ViewModel() {
    private val addkUseCase by lazy {  AddCategoryCaseUsen() }
    private val updateCheckedCategory by lazy {  UpdateCheckedCategory() }

    fun saveCategory(category: CategoryEnt){
        viewModelScope.launch {
            addkUseCase.addCategory(category)
        }
    }
    fun updateCheckedCt(category: CategoryEnt){
        category.isChecked=true
        viewModelScope.launch {
            updateCheckedCategory.updateCheckedCategory(category)
        }
    }
}