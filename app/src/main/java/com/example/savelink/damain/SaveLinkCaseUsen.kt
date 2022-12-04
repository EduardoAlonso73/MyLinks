package com.example.savelink.damain

import com.example.savelink.data.database.entities.SaveLinkRepository
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveLinkCaseUsen {
    private val domainRepository by lazy {  SaveLinkRepository()}

    suspend fun saveLink(linkEnt:LinkEnt)= withContext(Dispatchers.IO){
        domainRepository.saveLink(linkEnt)
    }
}