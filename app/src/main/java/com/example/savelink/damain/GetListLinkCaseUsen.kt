package com.example.savelink.damain

import androidx.lifecycle.LiveData
import com.example.savelink.data.database.entities.SaveLinkRepository
import com.example.savelink.data.entities.LinkEnt

class GetListLinkCaseUsen {
    private val domainRepository by lazy { SaveLinkRepository()}
     suspend fun getLink() =domainRepository.listAllLink()
}