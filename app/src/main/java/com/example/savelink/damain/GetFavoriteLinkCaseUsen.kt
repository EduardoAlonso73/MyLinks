package com.example.savelink.damain

import androidx.lifecycle.LiveData
import com.example.savelink.data.database.entities.SaveLinkRepository
import com.example.savelink.data.entities.LinkEnt

class GetFavoriteLinkCaseUsen {
    private val domainRepository by lazy { SaveLinkRepository()}
     fun getFavoriteLink(): LiveData<MutableList<LinkEnt>> =domainRepository.getFavoriteLink

}