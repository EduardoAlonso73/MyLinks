package com.example.savelink.ui.mainModule.mainViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savelink.damain.DeleteLink
import com.example.savelink.damain.GetListLinkCaseUsen
import com.example.savelink.damain.UpdateFavorite
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetLinkViewModel: ViewModel() {

        private val getListLinkCaseUse by lazy { GetListLinkCaseUsen() }
    private val updateFavorite by lazy { UpdateFavorite() }
    private val deleteLink by lazy { DeleteLink() }

    fun getListLink(): LiveData<MutableList<LinkEnt>> =getListLinkCaseUse.getLink()

    fun updateLinkFavorite(linkEnt:LinkEnt){
        linkEnt.isFavorite=!linkEnt.isFavorite
        viewModelScope.launch(Dispatchers.IO) {
            updateFavorite.updateLinkFavorite(linkEnt)
        }

    }
    fun deleteLink(linkEnt:LinkEnt){
        viewModelScope.launch(Dispatchers.IO) {
            deleteLink.deleteLink(linkEnt)
        }

    }
}