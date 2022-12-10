package com.example.savelink.ui.categoryModule.CategoryViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savelink.damain.DeleteLink
import com.example.savelink.damain.GetListCategoryCaseUsen
import com.example.savelink.damain.GetListLinkCaseUsen
import com.example.savelink.damain.UpdateFavorite
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetCategoryViewModel: ViewModel() {

    private val getListCategoCaseUse by lazy { GetListCategoryCaseUsen() }
    private val updateFavorite by lazy { UpdateFavorite() }
    private val deleteLink by lazy { DeleteLink() }

    fun getListCategory(): LiveData<MutableList<CategoryEnt>> =getListCategoCaseUse.getCategory()

  /*  fun updateLinkFavorite(linkEnt:LinkEnt){
        linkEnt.isFavorite=!linkEnt.isFavorite
        viewModelScope.launch(Dispatchers.IO) {
            updateFavorite.updateLinkFavorite(linkEnt)
        }

    }
    fun deleteLink(linkEnt:LinkEnt){
        viewModelScope.launch(Dispatchers.IO) {
            deleteLink.deleteLink(linkEnt)
        }

    }*/
}