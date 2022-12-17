package com.example.savelink.ui.mainModule.mainViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savelink.damain.*
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetLinkViewModel : ViewModel() {

    private val getListCategoCaseUse by lazy { GetListCategoryCaseUsen() }
    private val getListByCategoCaseUse by lazy { GetListByCategoryCaseUsen() }
    private val getListLinkCaseUse by lazy { GetListLinkCaseUsen() }
    private val updateFavorite by lazy { UpdateFavorite() }
    private val updateCategory by lazy { UpdateLinkCategory() }
    private val deleteLink by lazy { DeleteLink() }
    private var isAddBookToCategory:MutableLiveData<Boolean> = MutableLiveData()
    var headerCategory: MutableLiveData<String> = MutableLiveData()
    var link: MutableLiveData<LinkEnt> = MutableLiveData()
    var responseAction: MutableLiveData<String> = MutableLiveData()


    fun setHeaderCategory(category: String) {
        headerCategory.postValue(category)
    }

    fun setlink(linkEnt: LinkEnt) {
        link.postValue(linkEnt)
    }


    fun getListLink(): LiveData<MutableList<LinkEnt>> = getListLinkCaseUse.getLink()
    fun getListLinkCategory(category: String): LiveData<MutableList<LinkEnt>> =
        getListByCategoCaseUse.getLinkByCategory(category)


    fun getListCategory(): LiveData<MutableList<CategoryEnt>> = getListCategoCaseUse.getCategory()
    fun getListCategorySelect(): LiveData<MutableList<CategoryEnt>> = getListCategoCaseUse.getCategorySelect()

    fun updateLinkFavorite(linkEnt: LinkEnt) {
        linkEnt.isFavorite = !linkEnt.isFavorite
        viewModelScope.launch(Dispatchers.IO) {
            val response = updateFavorite.updateLinkFavorite(linkEnt)
            if(response==1){responseAction.postValue("Link Update success")}
        }

    }
    fun updateLinkCategoty(linkEnt: LinkEnt) {
        if(!linkEnt.isSavaToBook) linkEnt.isSavaToBook = true
        isAddBookToCategory.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = updateCategory.updateLinkCategory(linkEnt)
            if(response==1){responseAction.postValue("Link Update success")}
        }
    }

    fun deleteLink(linkEnt: LinkEnt) {
        viewModelScope.launch(Dispatchers.IO) {
            val response=deleteLink.deleteLink(linkEnt)
         if(response==1){responseAction.postValue("Link eliminado success")}
        }

    }


}