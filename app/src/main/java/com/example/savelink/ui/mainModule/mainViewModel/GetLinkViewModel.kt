package com.example.savelink.ui.mainModule.mainViewModel

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
    private val deleteLink by lazy { DeleteLink() }
    var headerCategory:MutableLiveData<String> = MutableLiveData()

    fun setHeaderCategory(category: String){
        headerCategory.postValue(category)
    }


    fun getListLink(): LiveData<MutableList<LinkEnt>> = getListLinkCaseUse.getLink()
    fun getListLinkCategory(category: String): LiveData<MutableList<LinkEnt>> = getListByCategoCaseUse.getLinkByCategory(category)


    fun getListCategory(): LiveData<MutableList<CategoryEnt>> = getListCategoCaseUse.getCategory()

    fun updateLinkFavorite(linkEnt: LinkEnt) {
        linkEnt.isFavorite = !linkEnt.isFavorite
        viewModelScope.launch(Dispatchers.IO) {
            updateFavorite.updateLinkFavorite(linkEnt)
        }
    }

    fun deleteLink(linkEnt: LinkEnt) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteLink.deleteLink(linkEnt)
        }

    }


}