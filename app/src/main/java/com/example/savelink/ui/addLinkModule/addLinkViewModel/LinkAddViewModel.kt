package com.example.savelink.ui.addLinkModule.addLinkViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savelink.damain.SaveLinkCaseUsen
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.launch

class LinkAddViewModel:ViewModel() {
    private val saveLinkUseCase by lazy {  SaveLinkCaseUsen() }

    fun saveLink(linkEnt: LinkEnt){
        viewModelScope.launch {
            saveLinkUseCase.saveLink(linkEnt)
        }
    }
}