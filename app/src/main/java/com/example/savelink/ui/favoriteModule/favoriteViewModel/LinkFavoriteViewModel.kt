package com.example.savelink.ui.favoriteModule.favoriteViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.savelink.damain.GetFavoriteLinkCaseUsen
import com.example.savelink.data.entities.LinkEnt

class LinkFavoriteViewModel: ViewModel() {

    private val getFavoriteLinkCaseUse by lazy { GetFavoriteLinkCaseUsen() }
    fun getListLinkFavori(): LiveData<MutableList<LinkEnt>> =getFavoriteLinkCaseUse.getFavoriteLink()




   /* fun updateLinkFavorite(linkEnt:LinkEnt){
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