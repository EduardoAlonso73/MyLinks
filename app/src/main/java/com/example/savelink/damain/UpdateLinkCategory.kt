package com.example.savelink.damain

import com.example.savelink.data.database.entities.SaveLinkRepository
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateLinkCategory {
    private val domainRepository by lazy {  SaveLinkRepository() }

    suspend fun updateLinkCategory(linkEnt: LinkEnt)= withContext(Dispatchers.IO){
        domainRepository.updateLinkCategory(linkEnt)
    }
}